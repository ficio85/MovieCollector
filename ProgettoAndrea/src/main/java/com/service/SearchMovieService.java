package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
	SearchMovieDAO searchMovieDAO;
	
	
	
	
	
	public List <MovieDTO> getListaFilm(SearchDTO search){

		List <String> codMovies = new ArrayList <String>();
		List <String> codResults = new ArrayList <String>();
		List<Map<String, Object>> prova2;
		List<Map<String, Object>> prova ;
	
		if(search.isSearchActor())
		{
			if(!codResults.isEmpty())
			{
				 prova2=searchMovieDAO.getMoviesByActor( search.getActors(),codResults);
			}
			else
			{
				List <String> provaString = searchMovieDAO.getMoviesByActor( search.getActors());
			}
		}
		


		return null;

	}



	public List<GenereDTO> getListaGeneri() {
		// TODO Auto-generated method stub
		
		List <GenereDTO> generi =genereDAO.getListaGeneri();
		return generi;
	}
}
