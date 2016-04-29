package com.util;

import java.util.ArrayList;
import java.util.List;

import com.dto.SearchDTO;
import com.form.SearchMovieForm;

public class SearchUtil {

	
	
	
	public static SearchDTO convertFromModelToSearchDTO(SearchMovieForm searchMovie)
	{
		SearchDTO searchDto = new SearchDTO();
		if(searchMovie.getActor()!=null && !searchMovie.getActor().trim().equals(""))
		{
			searchDto.setSearchActor(true);
			List <String> actors = new ArrayList <String>();
			for(int i=0;i<1;i++)
			{
				actors.add(searchMovie.getActor());
			}
			searchDto.setActors(actors);
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
		if(searchMovie.getGenere()!=null && !searchMovie.getGenere().trim().equals(""))
		{
			searchDto.setSearchGenre(true);
			List <String> genre = new ArrayList <String>();
			for(int i=0;i<1;i++)
			{
				genre.add(searchMovie.getGenere());
			}
			searchDto.setGenres(genre);
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
		return searchDto;

	}
}
