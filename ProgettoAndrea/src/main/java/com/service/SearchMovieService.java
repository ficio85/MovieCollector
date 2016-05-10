package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import com.dao.GenereDAO;
//import com.dao.GenereDAO;
import com.dao.SearchMovieDAO;
import com.dto.GenereDTO;
import com.dto.MovieDTO;
import com.dto.SearchDTO;

@Service("searchMovieService")
public class SearchMovieService {

	

	
	@Autowired
	@Qualifier("searchMovieDAO")
	SearchMovieDAO searchMovieDAO;
	

	@Autowired
	@Qualifier("genereDAO")
	GenereDAO genereDAO;
	
	
	
	public List <MovieDTO> getListaFilm(SearchDTO search){

		List <String> codMovies = new ArrayList <String>();
		List<MovieDTO> movies = null;

		int offset=search.getStart();
		int limit=search.getOffset();

		if(search.isSearchActor())
		{
			if(!codMovies.isEmpty())
			{
				codMovies=searchMovieDAO.getMoviesByActor( search.getActors(),codMovies,0,0,search.isAndActors(),false);
			}
			else
			{
				codMovies=searchMovieDAO.getMoviesByActor( search.getActors(),null,0,0,search.isAndActors(),false);
			}
		}
		if(search.isSearchDirector())
		{
			if(!codMovies.isEmpty())
			{
				codMovies=searchMovieDAO.getMoviesByDirector( search.getDirectors(),codMovies);
			}
			else
			{
				codMovies = searchMovieDAO.getMoviesByDirector( search.getDirectors());
			}
		}

		if(search.isSearchGenre())
		{
			if(!codMovies.isEmpty())
			{
				
				codMovies=searchMovieDAO.getMoviesByGenre( search.getGenres(),codMovies,0,0,search.isAndGenres(),false);
			}
			else
			{
				
				codMovies=searchMovieDAO.getMoviesByGenre( search.getGenres(),null, 0,0,search.isAndGenres(),false);
			}
		}
		if(search.isSearchYear())
		{
			if(!codMovies.isEmpty())
			{
				codMovies=searchMovieDAO.getMoviesByYear( search.getYears(), codMovies,0,0);
			}
			else
			{
				codMovies=searchMovieDAO.getMoviesByYear( search.getYears(),null, 0,0);
			}
		}
		if(!codMovies.isEmpty())
		{
			if(search.isCount())
			{
				search.setCountResult(searchMovieDAO.getCountMoviesByIndex(codMovies));
			}
			movies =searchMovieDAO.getMoviesByIndex(codMovies,offset, limit);
			completeMovies(movies);
		}

		return movies;

	}



	private void completeMovies(List<MovieDTO> movies) {

		
		for(MovieDTO movieDTO:movies)
		{
			movieDTO.setTitoloItaliano(searchMovieDAO.getMovieInternationalization(movieDTO.getMovieKey()));
			movieDTO.setActors(searchMovieDAO.getMovieActors(movieDTO.getMovieKey()));
			movieDTO.setGenre(searchMovieDAO.getMovieGenre(movieDTO.getMovieKey()));
			movieDTO.setDirectors(searchMovieDAO.getMovieDirector(movieDTO.getMovieKey()));
		}
	}



	public List<GenereDTO> getListaGeneri() {
		// TODO Auto-generated method stub
		
		List <GenereDTO> generi =genereDAO.getListaGeneri();
		return generi;
	}



}
