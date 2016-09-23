package com.service;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.dao.DirectorDAO;
import com.dao.InsertMovieDAO;
import com.dao.LabelDAO;
import com.dao.MovieRankDAO;
import com.dao.SearchMovieDAO;
import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.dto.UserMovieRateDTO;
import com.dto.WriterDTO;
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
	
	@Autowired
	@Qualifier("directorDAO")
	DirectorDAO directorDAO;

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
		//inizio calcolo dei vari parametri del film per aggiornare il gusto dell'utente
		
		
		//calcolo del genere
			
		ArrayList <String> indexes = new ArrayList <String>();
		indexes.add(movie);
		List<MovieDTO> movieComplete = searchMovieDAO.getMoviesByIndex(indexes, 0, 0, true);
		//calcolo del genere
		
		//insertUserGenreRate(movie,rate);
		
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
	
	public void updateUserRate(String codPers) {
		// TODO Auto-generated method stub
		//selezione di tutti i film votati
		List<UserMovieRateDTO> userMovieList = rateDAO.getUserMovieRate(codPers);
		//aggiornamento dei generi
		HashMap <String,Float> generiMap;
		HashMap <String,Integer> countGeneriMap;

		HashMap <String, Integer> countActorsMap = new HashMap <String, Integer>();
		HashMap <String, Float> actorsMap=new HashMap <String, Float>();

		HashMap <String, Integer> countDirectorsMap=new HashMap <String, Integer>();
		HashMap <String, Float> directorsMap=new HashMap <String, Float>();
		
		HashMap <String, Integer> countWritersMap=new HashMap <String, Integer>();
		HashMap <String, Float> writersMap=new HashMap <String, Float>();
		for(UserMovieRateDTO movieRate:userMovieList)
		{
					
			String indexMovie=movieRate.getMovie();
			float rate = movieRate.getRate();
			List <ActorDTO> actors =searchMovieDAO.getMovieActors(indexMovie);
			List <WriterDTO> writers= searchMovieDAO.getMovieWriter(indexMovie);
			List <GenereDTO> genres= searchMovieDAO.getMovieGenre(indexMovie);
			
			for(ActorDTO actor:actors)
			{
				String actorName = actor.getName();
				int count=0;
				 count = countActorsMap.get(actorName);
				countActorsMap.put(actorName, ++count);
				if(actorsMap.get(actorName)!=null)
				{
					float averageRate=(actorsMap.get(actorName)+rate)/countActorsMap.get(actorName);
					actorsMap.put(actorName, averageRate);
				}
				else
				{
					actorsMap.put(actorName, rate);
				}
				
			}
			for(WriterDTO writer:writers)
			{
				String writerName = writer.getName();
				int count=0;
				 count = countWritersMap.get(writerName);
				countWritersMap.put(writerName, ++count);
				if(actorsMap.get(writerName)!=null)
				{
					float averageRate=(actorsMap.get(writerName)+rate)/countActorsMap.get(writerName);
					actorsMap.put(writerName, averageRate);
				}
				else
				{
					actorsMap.put(writerName, rate);
				}
				
			}
			
			for(WriterDTO writer:writers)
			{
				String writerName = writer.getName();
				int count=0;
				 count = countWritersMap.get(writerName);
				countActorsMap.put(writerName, ++count);
			}
			searchMovieDAO.getMovieWriter(indexMovie);
			
			
		}
		
	}

	private List<GenereDTO> getListaGeneri() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	private void insertUserGenreRate(MovieDTO movie, float rate, String codPers) {
		// TODO Auto-generated method stub
		List<GenereDTO> genres = movie.getGenre();
		for(GenereDTO genre : genres)
		{
			
		}
	}

	public RateResponse insertUserActorRate(String codPers, String actor, float rate) {
		RateResponse rateResponse = new RateResponse();		
		rateDAO.deleteUserActorRate(codPers,actor);
		rateDAO.insertUserActorRate(codPers, actor, rate);
		float rateOld=actorDAO.getActorsDetail(actor).getRate();
		float sommaRate= rateDAO.getSumUserActorRate(actor);
		int countRate = rateDAO.getCountUserActorRate(actor);
		float mediaRate= sommaRate/countRate;
		if(mediaRate!= rateOld)
		{
			rateResponse.setRateChanged(true);
			rateDAO.updateActorRank(actor, mediaRate);
		}
		rateResponse.setNewRate(mediaRate);
		rateResponse.setNewRateString(mediaRate);
		rateResponse.setOldRate(rateOld);
		return rateResponse;

	}
	
	public RateResponse insertUserDirectorRate(String codPers, String director, float rate) {
		RateResponse rateResponse = new RateResponse();		
		rateDAO.deleteUserDirectorRate(codPers,director);
		rateDAO.insertUserDirectorRate(codPers, director, rate);
		float rateOld=directorDAO.getDirectorDetail(director).getRate();
		float sommaRate= rateDAO.getSumUserDirectorRate(director);
		int countRate = rateDAO.getCountUserDirectorRate(director);
		float mediaRate= sommaRate/countRate;
		if(mediaRate!= rateOld)
		{
			rateResponse.setRateChanged(true);
			rateDAO.updateDirectorRank(director, mediaRate);
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
		DirectorDTO directorInspected = JsoupUtil.imdbInspectDirector(indexMovie);
		director.setImdbIndex(directorInspected.getImdbIndex());
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
