package com.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import com.dto.TipoData;
import com.eccezione.WarningException;
import com.util.JsoupUtil;


@Service("translateServiceSync")
public class TranslateServiceSync {
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
	
	public void insertTranslation(List <String> values,String type) throws Exception{

		List<MovieDTO> movies;
		for(String value:values)
		{
			try{
				movies = JsoupUtil.wikiInspect(value,type);
				System.out.println("Inizio processo di traduzione");
				System.out.println(type);
				System.out.println(value);
				System.out.println("Dimensione "+values.size());
				if(type.equals("actor"))
				{
					if(!checkTranslatedActor(value))
					{
						translateMoviesByActor(movies, value);	
						updateTranslatedActor(value);
					}

				}
				else if(type.equals("director"))
				{
					if(!checkTranslatedDirector(value))
					{
						translateMoviesByDirector(movies, value);	
						updateTranslatedDirector(value);

					}
				}
			}
			catch(Exception ex)
			{
				WarningException warn = new WarningException();
				warn.setMessaggio(ex.getMessage());
				warn.setMovieKey("NON PRESENTE");
				warn.setTipo("inserimento"+value+type);
				logDAO.insertLogWarning(warn);
			}
		}

		System.out.println("I'm done!");
	}
	
	

	private void updateTranslatedDirector(String value) {
		// TODO Auto-generated method stub
		
		Timestamp time= new Timestamp(new Date().getTime());
		directorDAO.updateDirectorbyWiki(1, time, value);
		
	}
	
	
	private void updateTranslatedActor(String value) {
		// TODO Auto-generated method stub
		
		Timestamp time= new Timestamp(new Date().getTime());
		actorDAO.updateActorbyWiki(1, time, value);
		
	}



	




	private boolean checkTranslatedActor(String value) {
		// TODO Auto-generated method stub
		ActorDTO actor =actorDAO.getActorsDetail(value);
		TipoData dataOggi = new TipoData(new Date());
		TipoData dataUpdateWiki = null;
		if(actor.getTimewCompleto()!=null)
		 dataUpdateWiki = new TipoData(actor.getTimewCompleto());
		if(actor.getwCompleto()!=1)
		{
			return false;
		}
		else
		{
			if(dataUpdateWiki!=null && (dataOggi.getMonth()-dataUpdateWiki.getMonth()>4))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
			
					
			
	}


	private boolean checkTranslatedDirector(String director) {
		// TODO Auto-generated method stub
		DirectorDTO directorDTO =directorDAO.getDirectorDetail(director);
		TipoData dataOggi = new TipoData(new Date());
		TipoData dataUpdateWiki= null;
		if(directorDTO.getTimewCompleto()!=null)
		 dataUpdateWiki = new TipoData(directorDTO.getTimewCompleto());
		if(directorDTO.getwCompleto()!=1)
		{
			return false;
		}
		else
		{
			if(dataUpdateWiki!=null && (dataOggi.getMonth()-dataUpdateWiki.getMonth()>4))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
			
					
			
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
				warn.setMovieKey("NONPRESENTE");
				warn.setTipo("INSERIMENTO LOCALIZZAZIONE DA WIKIPEDIA");
				warn.setMovieTitle(movie.getTitle());
				warn.setMessaggio("Titolo nn reperito in db "+movie.getTitle()+" director "+director);
				logDAO.insertLogWarning(warn);
			}

		}

	}
	
	
	
	
	public void inspectImdbForActor(String indexMovie,ActorDTO actor) throws Exception{
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
	

	}

	public void inspectImdbForDirector(String indexMovie,DirectorDTO director) throws Exception{
		System.out.println("Inizio thread");
		DirectorDTO directorInspected = JsoupUtil.imdbInspectDirector(indexMovie);
		director.setImdbIndex(directorInspected.getImdbIndex());
		System.out.println("I'm done!");
	

	}
	
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void translateMoviesByActor(List <MovieDTO> movies, String actor){

		for(MovieDTO movie:movies)
		{
			//la cosa più scontata

			List <MovieDTO> movieList  = searchMovieDAO.getMovieByActorTitle(movie.getTitle(), actor);
			
			//aggiorno l'internazionalizzazione
			if(movieList.size()==1)
			{
				movie.setMovieKey(movieList.get(0).getMovieKey());
				internationalizationDAO.updateInternationalization(movie);
				
			}
			else
			{
				WarningException warn = new WarningException();
				warn.setMovieKey("NONPRESENTE");
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
}
