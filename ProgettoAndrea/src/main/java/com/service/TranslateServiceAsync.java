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
import com.dao.DirectorDAO;
import com.dao.InsertMovieDAO;
import com.dao.InternationalizationDAO;
import com.dao.LabelDAO;
import com.dao.LogDAO;
import com.dao.MovieRankDAO;
import com.dao.SearchMovieDAO;
import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.MovieDTO;
import com.eccezione.WarningException;
import com.util.JsoupUtil;

@EnableAsync
@Service("translateServiceAsync")
public class TranslateServiceAsync {
	@Autowired
	@Qualifier("insertMovieDAO")
	InsertMovieDAO insertMovieDAO;	

	@Autowired
	@Qualifier("searchMovieDAO")
	SearchMovieDAO searchMovieDAO;

	@Autowired
	@Qualifier("searchMovieService")
	SearchMovieService searchMovieService;
	
	
	@Autowired
	@Qualifier("rateDAO")
	MovieRankDAO rateDAO;

	@Autowired
	@Qualifier("labelDAO")
	LabelDAO labelDAO;

	@Autowired
	@Qualifier("actorDAO")
	ActorDAO actorDAO;
	
	@Autowired
	@Qualifier("directorDAO")
	DirectorDAO directorDAO;
	
	@Autowired
	@Qualifier("logDAO")
	LogDAO logDAO;
	
	@Autowired
	@Qualifier("internazionalizationDAO")
	InternationalizationDAO internationalizationDAO;
	
	@Async
	public Future<Boolean> insertTranslation(List <String> values,String type) throws Exception{
		System.out.println("Inizio thread");
		List<MovieDTO> movies;
		for(String value:values)
		{
			movies = JsoupUtil.wikiInspect(value,type);
			if(type.equals("actor"))
			{
				translateMoviesByActor(movies, value);	

			}
			else if(type.equals("director"))
			{
				translateMoviesByDirector(movies, value);	
			}
		}

		System.out.println("I'm done!");
		return new AsyncResult<Boolean>(true);


	}
	
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void translateMoviesByDirector(List <MovieDTO> movies, String director){

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
				internationalizationDAO.updateInternationalization(movie);
			}
			else
			{
				WarningException warn = new WarningException();
				warn.setTipo("INSERIMENTO LOCALIZZAZIONE DA WIKIPEDIA");
				warn.setMovieTitle(movie.getTitle());
				warn.setMessaggio("Titolo nn reperito in db"+" director "+director);
				logDAO.insertLogWarning(warn);
			}

		}

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
	
//	@Async
//	public Future<Boolean> inspectImdbForDirector(String indexMovie,DirectorDTO director) throws Exception{
//		
//		System.out.println("I'm done!");
//		return new AsyncResult<Boolean>(true);
//
//	}
	
	@Async
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void translateMoviesByActor(List <MovieDTO> movies, String actor){

		for(MovieDTO movie:movies)
		{
			//la cosa più scontata

			List <MovieDTO> movieList  = searchMovieDAO.getMovieByActorTitle(movie.getTitle(), actor);
			
			
//			if(movieList==null || movieList.isEmpty())
//			{
//				movieList= searchMovieDAO.getMovieByTitleAndYear(movie.getTitle(), movie.getYear());
//			}
//			//provo a prenderlo per regista
//			if(movieList==null || movieList.isEmpty())
//			{
//				movieList = searchMovieDAO.getMovieByDirectorTitle(movie.getTitle(), movie.getDirectors());
//			}
//			//provo a prenderlo per solo regista e anno (magari per film con solo titolo in italiano)
//			if(movieList==null || movieList.isEmpty())
//			{
//				movieList = searchMovieDAO.getMovieByDirectorActor(actor,movie.getDirectors(), movie.getYear());
//			}
//			//ultimo tentativo rilasso il vincolo dell'anno di rilascio (magari per registi scritti strano)
//			if(movieList==null || movieList.isEmpty())
//			{
//				List <Integer >movieYears = getMovieYears(movie.getYear());
//				movieList = searchMovieDAO.getMovieByTitleAndYears(movie.getTitle(), movieYears);
//			}
			//aggiorno l'internazionalizzazione
			if(movieList.size()==1)
			{
				movie.setMovieKey(movieList.get(0).getMovieKey());
				
			}
			else
			{
				WarningException warn = new WarningException();
				warn.setTipo("INSERIMENTO LOCALIZZAZIONE DA WIKIPEDIA");
				warn.setMovieTitle(movie.getTitle());
				warn.setMessaggio("Titolo nn reperito in db"+movie.getTitle()+" actor "+actor);
				
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
}
