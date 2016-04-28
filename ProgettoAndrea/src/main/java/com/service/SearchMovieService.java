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
		
		if(search.isSearchActor())
		{
			if(!codMovies.isEmpty())
			{
				codMovies.addAll(searchMovieDAO.getMoviesByActor( search.getActors(),codMovies));
			}
			else
			{
				codMovies.addAll(searchMovieDAO.getMoviesByActor( search.getActors()));
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
				codMovies.addAll(searchMovieDAO.getMoviesByGenre( search.getGenres(),codMovies));
			}
			else
			{
				codMovies.addAll(searchMovieDAO.getMoviesByGenre( search.getGenres()));
			}
		}
		if(search.isSearchYear())
		{
			if(!codMovies.isEmpty())
			{
				codMovies.addAll(searchMovieDAO.getMoviesByYear( search.getYears(), codMovies));
			}
			else
			{
				codMovies.addAll(searchMovieDAO.getMoviesByYear( search.getYears()));
			}
		}
		List <MovieDTO> movies =searchMovieDAO.getMoviesByIndex(codMovies);
		completeMovies(movies);
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
