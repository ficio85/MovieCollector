package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ActorDAO;
import com.dao.InsertMovieDAO;
import com.dao.LabelDAO;
import com.dao.SearchMovieDAO;
import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.eccezione.WarningException;
import com.util.JsoupUtil;

@EnableAsync
@Service("insertMovieService")
public class InsertMovieService {

	@Autowired
	@Qualifier("insertMovieDAO")
	InsertMovieDAO insertMovieDAO;	

	@Autowired
	@Qualifier("searchMovieDAO")
	SearchMovieDAO searchMovieDAO;
	
	
	@Autowired
	@Qualifier("labelDAO")
	LabelDAO labelDAO;

	@Async
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void updateMoviesByActor(List <MovieDTO> movies, String actor){

		for(MovieDTO movie:movies)
		{
			//la cosa più scontata

			List <MovieDTO> movieList  = searchMovieDAO.getMovieByActorTitle(movie.getTitle(), actor);
			if(movieList==null || movieList.isEmpty())
			{
				movieList= searchMovieDAO.getMovieByTitleAndYear(movie.getTitle(), movie.getYear());
			}
			//provo a prenderlo per regista
			if(movieList==null || movieList.isEmpty())
			{
				movieList = searchMovieDAO.getMovieByDirectorTitle(movie.getTitle(), movie.getDirectors());
			}
			//provo a prenderlo per solo regista e anno (magari per film con solo titolo in italiano)
			if(movieList==null || movieList.isEmpty())
			{
				movieList = searchMovieDAO.getMovieByDirectorActor(actor,movie.getDirectors(), movie.getYear());
			}
			//ultimo tentativo rilasso il vincolo dell'anno di rilascio (magari per registi scritti strano)
			if(movieList==null || movieList.isEmpty())
			{
				List <Integer >movieYears = getMovieYears(movie.getYear());
				movieList = searchMovieDAO.getMovieByTitleAndYears(movie.getTitle(), movieYears);
			}
			//aggiorno l'internazionalizzazione
			if(movieList.size()==1)
			{
				movie.setMovieKey(movieList.get(0).getMovieKey());
				insertMovieDAO.updateInternationlization(movie);
				if(!insertMovieDAO.isMovieActorRel(movie,actor))
				{
					int index = insertMovieDAO.getMovieActorIndex(movie, actor);
					insertMovieDAO.updateMovieActorsRel(movie, actor, index);
				}
			}
			else
			{
				WarningException warn = new WarningException();
				warn.setTipo("INSERIMENTO LOCALIZZAZIONE DA WIKIPEDIA");
				warn.setMovieTitle(movie.getTitle());
				warn.setMessaggio("Titolo nn reperito in db");
			}

		}


	}

	private List<Integer> getMovieYears(int year) {
		// TODO Auto-generated method stub
		List <Integer> years= new ArrayList <Integer>();
		int yearPrec= year-1;
		int yearSuc = year+1;
		years.add(yearPrec);
		years.add(yearSuc);
		years.add(year);
		return years;

	}
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void updateMoviesByDirector(List <MovieDTO> movies, String director){

		List <DirectorDTO>directorList = new ArrayList <DirectorDTO>();
		DirectorDTO directorDto = new DirectorDTO();
		directorDto.setName(director);
		directorList.add(directorDto);
		for(MovieDTO movie:movies)
		{
			//la cosa più scontata

			List <MovieDTO> movieList  = searchMovieDAO.getMovieByDirectorTitle(movie.getTitle(), directorList);

			//aggiorno l'internazionalizzazione
			if(movieList.size()==1)
			{
				movie.setMovieKey(movieList.get(0).getMovieKey());
				insertMovieDAO.updateInternationlization(movie);

			}
			else
			{
				WarningException warn = new WarningException();
				warn.setTipo("INSERIMENTO LOCALIZZAZIONE DA WIKIPEDIA");
				warn.setMovieTitle(movie.getTitle());
				warn.setMessaggio("Titolo nn reperito in db");
			}

		}


	}

	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void insertUserLabel(String codPers, String movie,ArrayList <LabelDTO> labels) throws Exception{

		labelDAO.deleteUserLaber(codPers,movie);
		for(LabelDTO label:labels)
		{
			labelDAO.insertUserLaber(codPers,movie, label);
		}
		updateLabelTable(movie,labels);
		
	}


	@Async
	public Future<Boolean> insertTranslation(List <String> values,String type) throws Exception{
		System.out.println("Inizio thread");
		List<MovieDTO> movies;
		for(String value:values)
		{
			movies = JsoupUtil.wikiInspect(value,type);
			if(type.equals("actor"))
			{
				updateMoviesByActor(movies, value);	

			}
			else if(type.equals("director"))
			{
				updateMoviesByDirector(movies, value);	
			}
		}

		System.out.println("I'm done!");
		return new AsyncResult<Boolean>(true);


	}

	@Async
	public Future<Boolean> updateLabelTable(String movie,List <LabelDTO> labels) throws Exception{
		System.out.println("Inizio thread");
		List<LabelDTO> oldLabels = labelDAO.getListaLabelbyMovie(movie);
		
		boolean isSame=true;
		
		for(int j=0;j<oldLabels.size();j++)
		{
			if(!oldLabels.get(j).equals(labels.get(j)))
			{
				isSame=false;
			}
		}
		if(!isSame)
		{
			labelDAO.deleteLabelsFromMovie(movie);
			for(LabelDTO label : labels)
			{
				labelDAO.updateLabelsbyMovie(movie, label.getName());
			}
		}
		System.out.println("I'm done!");
		return new AsyncResult<Boolean>(true);
	}

	
	
	@Async
	public Future<Boolean> inspectImdb(String indexMovie) throws Exception{
		System.out.println("Inizio thread");
		List<MovieDTO> movies;
		List <ActorDTO> actors = JsoupUtil.imdbInspect(indexMovie);
		System.out.println("I'm done!");
		return new AsyncResult<Boolean>(true);


	}

//	public void deleteUserLabel(String codPers, String movie, ArrayList<LabelDTO> labels) {
//		// TODO Auto-generated method stub
//		for(LabelDTO label:labels)
//		{
//			insertMovieDAO.deleteUserLaber(codPers,movie, label);
//
//		}	
//	}


}
