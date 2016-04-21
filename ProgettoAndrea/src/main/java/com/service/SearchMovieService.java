package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenereDAO;
import com.dao.InsertMovieDAO;
import com.dao.SearchMovieDAO;
import com.dto.GenereDTO;
import com.dto.MovieDTO;
import com.dto.SearchDTO;

@Service("searchMovieService")
public class SearchMovieService {

	
	@Autowired
	@Qualifier("genereDAO")
	GenereDAO genereDAO;
	
	@Autowired
	@Qualifier("searchMovieDAO")
	GenereDAO searchMovieDAO;
	
	public List <GenereDTO> getListaGeneri(){
		return genereDAO.getListaGeneri();
		
	}
	
	
	
	public List <MovieDTO> getListaFilm(SearchDTO search){

		List <MovieActorReceiver> movieActorReceiver;
		List <MovieGenreReceiver> movieGenreReceiver;
		List <String> codMovies = new ArrayList <String>();
		List <String> codResults = new ArrayList <String>();
		if(search.isSearchGenre())
		{
			if(!codResults.isEmpty())
			{
				movieGenreReceiver =searchMovieDAO.getMovieGenreByCod( codResults);
			}
			else
			{
				movieGenreReceiver =searchMovieDAO.getMovieGenre();
			}
		}
		codResults= parseMoviesFromReceiver(movieGenreReceiver);
		if(search.isSearchActor())
		{
			if(!codResults.isEmpty())
			{
				movieActorReceiver =searchMovieDAO.getMovieActorByCod(codResults);
			}
			else
			{
				movieActorReceiver =searchMovieDAO.getMovieActor();
			}
		}


		return null;

	}



	private List<String> parseMoviesFromReceiver(List<MovieGenreReceiver> movieGenreReceiver) {
		// TODO Auto-generated method stub
		return null;
	}
}
