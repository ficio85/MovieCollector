package com.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MovieDAO;
import com.dto.MovieDTO;

@Service("movieService")
public class MovieService {
	
	@Autowired
	@Qualifier("movieDAO")
	MovieDAO movieDAO;
	
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public int insertMovie(MovieDTO movieDto)
	{
		String result = movieDAO.isMoviePresent(movieDto);
		if(result.trim().equals("ERROR"))
		{
			return 3;
		}
		else if (result.trim().equals("1"))
		{
			return 2;
		}
		else if (result.trim().equals("EMPTY"))
		{
			//popolamento tipologica nazioni
			
			for(String country : movieDto.getCountries())
			{
				if(!movieDAO.isPresentCountry(country))
				{
					movieDAO.insertCountry(country);
				}
			}
			//popolamento tipologica generi
			for(String genre : movieDto.getGenre())
			{
				if(!movieDAO.isPresentGenre(genre))
				{
					movieDAO.insertGenre(genre);
				}
			}
			//popolamento tipologica attori
			for(String actor : movieDto.getActors())
			{
				if(!movieDAO.isPresentActor(actor.trim()))
				{
					movieDAO.insertActor(actor.trim());
				}
			}
			movieDAO.insertMovie(movieDto);
			movieDAO.insertMovieGenRel(movieDto);
			movieDAO.insertMovieActorsRel(movieDto);
			movieDAO.insertInternazionalization(movieDto);
			return 1;
		}
		return 0;
		
	}
	
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void cleanDB()
	{
		cleanDbRel();
		movieDAO.deleteAllMovies();
		movieDAO.deleteGenres();
		movieDAO.deleteAllCountries();
		movieDAO.deleteActors();
	}
	
	
	
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void cleanDbRel()
	{
		  movieDAO.deleteMovGenRel();
		  movieDAO.deleteMovieActorsRel();
	}

	public void generateDbByActor(List<MovieDTO> moviesActor, String actor) {
	
		
	}

	public MovieDTO getInternationalization(MovieDTO film) {
		MovieDTO movieDTOtoUpdate = movieDAO.getInternazionalization(film).get(0);
		return movieDTOtoUpdate;
	}
	
	

	public void updateMovieInternationalization(String actor, MovieDTO film) {
		movieDAO.updateInternazionalization(film);
		if(!movieDAO.isPresentMovieActorsRel(film, actor))
		{
			List <String> actors = new ArrayList <String>();
			actors.add(actor);
			film.setActors(actors );
			movieDAO.insertMovieActorsRel(film);
		}
	}
	
	
	
}
