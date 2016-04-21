package com.util;

import java.util.ArrayList;
import java.util.List;

import com.dto.SearchDTO;
import com.form.SearchMovieForm;

public class SearchUtil {

	
	
	
	public static SearchDTO convertFromModelToSearchDTO(SearchMovieForm searchMovie)
	{
		SearchDTO searchDto = new SearchDTO();
		if(searchMovie.getActor()!=null && searchMovie.getActor().trim().equals(""))
		{
			searchDto.setSearchActor(true);
			List <String> actors = new ArrayList <String>();
			for(int i=0;i<1;i++)
			{
				actors.add(searchMovie.getActor());
			}
			searchDto.setActors(actors);
		}
		return searchDto;
		
	}
}
