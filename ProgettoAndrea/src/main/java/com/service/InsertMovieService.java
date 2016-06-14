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

import com.controller.ProgramTvMovieDTO;
import com.dao.ActorDAO;
import com.dao.InsertMovieDAO;
import com.dao.LabelDAO;
import com.dao.MovieRankDAO;
import com.dao.SearchMovieDAO;
import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.eccezione.WarningException;
import com.jsonResponse.RateResponse;
import com.util.JsoupUtil;
import com.util.SessionUtil;

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
	@Qualifier("rateDAO")
	MovieRankDAO rateDAO;
	
	@Autowired
	@Qualifier("labelDAO")
	LabelDAO labelDAO;

	@Autowired
	@Qualifier("actorDAO")
	ActorDAO actorDAO;
	
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

		public void insertInternationalization(MovieDTO movie)
		{
			insertMovieDAO.updateInternationlization(movie);
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

	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public RateResponse insertUserRate(String codPers, String movie,float rate,float rateOld) throws Exception{
		RateResponse rateResponse = new RateResponse();
		rateDAO.deleteUserRate(codPers,movie);
		rateDAO.insertUserRate(codPers, movie, rate);
		float sommaRate= rateDAO.getSumUserRate(movie);
		int countRate = rateDAO.getCountUserRate(movie);

		float mediaRate= sommaRate/countRate;
		if(mediaRate!= rateOld)
		{
			rateResponse.setRateChanged(true);
			rateDAO.updateMovieRank(movie, mediaRate);
		}
		rateResponse.setNewRate(mediaRate);
		rateResponse.setNewRateString(mediaRate);
		rateResponse.setOldRate(rateOld);
		return rateResponse;
		
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
	public Future<Boolean> updateLabelTable( String movie,List <LabelDTO> labels) throws Exception{
		System.out.println("Inizio thread");
		List<LabelDTO> oldLabels = labelDAO.getListaUserLabelbyMovie(movie);
		
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
			int i=1;
			for(LabelDTO label : labels)
			{
				labelDAO.updateLabelsbyMovie(movie, label.getName(), i);
				i++;
			}
		}
		System.out.println("I'm done!");
		return new AsyncResult<Boolean>(true);
	}

	
	
	public Future<Boolean> inspectImdbForActor(String indexMovie,ActorDTO actor) throws Exception{
		System.out.println("Inizio thread");
		List<MovieDTO> movies;
		List <ActorDTO> actors = JsoupUtil.imdbInspect(indexMovie);
		ActorDTO actorToConsider = null;
		for(ActorDTO actorMovie: actors)
		{ 
			if (actorMovie.getName().equals(actor.getName()))
			{
				actorToConsider = actorMovie ;
				break;
			}
		}
		actor.setImdbIndex(actorToConsider.getImdbIndex());
		System.out.println("I'm done!");
		return new AsyncResult<Boolean>(true);

	}

	public Future<Boolean> inspectImdbForDirector(String indexMovie,DirectorDTO director) throws Exception{
		System.out.println("Inizio thread");
		List<MovieDTO> movies;
		List <DirectorDTO> directors = JsoupUtil.imdbInspect(indexMovie);
		DirectorDTO directorToConsider = null;
		for(DirectorDTO directorMovie: directors)
		{ 
			if (directorMovie.getName().equals(director.getName()))
			{
				directorToConsider = directorMovie ;
				break;
			}
		}
		director.setImdbIndex(directorToConsider.getImdbIndex());
		System.out.println("I'm done!");
		return new AsyncResult<Boolean>(true);

	}
	
	
	public void completeActors(List<ActorDTO> actors, String movieKey) {
		// TODO Auto-generated method stub
		for(ActorDTO actor:actors)
		{
			insertMovieDAO.deleteMovieActorsRel(movieKey);
			insertMovieDAO.insertMovieActorsRel( actor, movieKey);
			//actorDAO.updateActor(actor);

		}
	}

	public void insertProgrammiTv(List<ProgramTvMovieDTO> programmi) {
		// TODO Auto-generated method stub
		for(ProgramTvMovieDTO program : programmi)
		{
			if(program.getMovie().getMovieKey()!=null)
			{
				insertMovieDAO.insertProgrammaTv(program);
			}
		}
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
