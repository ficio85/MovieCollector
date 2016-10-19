package com.algorithm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.GenereDTO;
import com.dto.MovieDTO;
import com.service.SearchMovieService;

public class MovieLikeAlgorithm {

	@Autowired
	@Qualifier("searchMovieService")
	private SearchMovieService searchMovieService;
	
	
	public static int getMovieLikeRate(MovieDTO movie,String user)
	{
		List <DirectorDTO> directors =movie.getDirectors();
		List <GenereDTO> genres= movie.getGenre();
		List <ActorDTO> actors=movie.getActors();
		//prendo il regista principale
		DirectorDTO mainDirector=directors.get(0);
		
		movie.getActors();
		
		return 0;
		
	}
}
