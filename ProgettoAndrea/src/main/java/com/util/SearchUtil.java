package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dto.SearchDTO;
import com.form.SearchMovieForm;

public class SearchUtil {

	
	
	
	public static SearchDTO convertFromModelToSearchDTO(SearchMovieForm searchMovie, HttpServletRequest request)
	{
		String []genres=request.getParameterValues("genere");
		
		String[] actors=request.getParameterValues("actor");
		String unisciGeneri=request.getParameter("unisciGeneri");
		SearchDTO searchDto = new SearchDTO();
		if(actors!=null && actors.length!=0)
		{
			searchDto.setSearchActor(true);
			
			searchDto.setActors(new ArrayList<String>(Arrays.asList(actors)));
		}
		if(searchMovie.getDirector()!=null && !searchMovie.getDirector().trim().equals(""))
		{
			searchDto.setSearchDirector(true);
			List <String> directors = new ArrayList <String>();
			for(int i=0;i<1;i++)
			{
				directors.add(searchMovie.getDirector());
			}
			searchDto.setDirectors(directors);
		}
		if(genres!=null && genres.length!=0 &&!(genres.length==1 && genres[0].trim().equals("")))
		{
			searchDto.setSearchGenre(true);
			if(request.getParameter("unisciGeneri")!=null && request.getParameter("unisciGeneri").equals("1"))
			{
				searchDto.setAndGenres(true);
			}
			searchDto.setGenres(new ArrayList<String>(Arrays.asList(genres)));
		}
		if(searchMovie.getYear()!=0 )
		{
			searchDto.setSearchYear(true);
			List <Integer> years = new ArrayList <Integer>();
			for(int i=0;i<1;i++)
			{
				years.add(searchMovie.getYear());
			}
			searchDto.setYears(years);
		}
		//controllo se devo fare la count, se ho spinto sulla submit
		if(request.getParameter("actionHidden")!=null && request.getParameter("actionHidden").equals("search"))
		{
			searchDto.setCount(true);
		}
		searchDto.setOffset(15);
		if(request.getParameter("curPage")!=null)
		{
			searchDto.setCurrPage(Integer.parseInt(request.getParameter("curPage")));
		}
		return searchDto;

	}
}
