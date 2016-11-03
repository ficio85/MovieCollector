package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dto.SearchDTO;
import com.form.SearchMovieForm;

public class SearchUtil {

	
	
	
	public static SearchDTO convertFromModelToSearchDTO(HttpServletRequest request)
	{
		String []genres=request.getParameterValues("genere");
		
		String[] actors=request.getParameterValues("actor");
		String director = request.getParameter("director");
		String []yearArray= request.getParameterValues("year");
		String unisciGeneri=request.getParameter("unisciGeneri");
		String unisciAttori=request.getParameter("unisciAttori");
		String title = request.getParameter("movieTitle");
	
		String[] labels=request.getParameterValues("label");
		String unisciLabel= request.getParameter("unisciLabel");
		
		SearchDTO searchDto = new SearchDTO();
		if(title!=null && !title.trim().equals(""))
		{
			searchDto.setSearchTitle(true);
			searchDto.setTitle(title);
		}
		if(actors!=null && actors.length!=0 &&!(actors.length==1 && actors[0].trim().equals("")))
		{
			searchDto.setSearchActor(true);
			if(request.getParameter("unisciAttori")!=null && request.getParameter("unisciAttori").equals("1"))
			{
				searchDto.setAndActors(true);
			}
			searchDto.setActors(new ArrayList<String>(Arrays.asList(actors)));
		}
		if(labels!=null && labels.length!=0 &&!(labels.length==1 && labels[0].trim().equals("")))
		{
			searchDto.setSearchLabel(true);
			if(request.getParameter("unisciLabel")!=null && request.getParameter("unisciLabel").equals("1"))
			{
				searchDto.setAndLabels(true);
			}
			searchDto.setLabels(new ArrayList<String>(Arrays.asList(labels)));
		}
		if(director!=null && !director.trim().equals(""))
		{
			searchDto.setSearchDirector(true);

			searchDto.setDirectors(new ArrayList<String>(Arrays.asList(director)));
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
		if(yearArray!=null && yearArray.length!=0 &&!(yearArray.length==1 && yearArray[0].trim().equals("")) &&!(yearArray.length==1 && yearArray[0].trim().equals("0")))
		{
			searchDto.setSearchYear(true);
			List <Integer> years = new ArrayList <Integer>();
			for(int i=0;i<yearArray.length;i++)
			{
				years.add(Integer.parseInt(yearArray[i]));
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
