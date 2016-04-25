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
import com.dao.SearchMovieDAO;
import com.dto.MovieDTO;
import com.eccezione.WarningException;

@EnableAsync
@Service("insertMovieService")
public class InsertMovieService {
	
	@Autowired
	@Qualifier("insertMovieDAO")
	InsertMovieDAO insertMovieDAO;	
	
	@Autowired
	@Qualifier("searchMovieDAO")
	SearchMovieDAO searchMovieDAO;	
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

@Async
public Future<Boolean> prova(){
	System.out.println("Inizio thread");
	// Demonstrate that our beans are being injected
	for(int j=0;j<100;j++)
	{ 
		System.out.println("PROVA.ASINC");

		
		try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		e.printStackTrace();
		System.out.println("PROVA.ASINC");

	}
	}


	System.out.println("I'm done!");
	return new AsyncResult<Boolean>(true);


}

}
