package com.util;

import java.util.ArrayList;
import java.util.List;

import com.dto.ActorDTO;
import com.dto.MovieDTO;

public class MovieGeneratorUtil {

	
	public static List<String> parseCountries(String countries) {
		// TODO Auto-generated method stub
		List <String> countryList= new ArrayList <String>();
		String[] countryArray = countries.split(",");
		for(int i=0;i<countryArray.length;i++)
		{
			countryList.add(countryArray[i]);
		}
		return countryList;
	}

	public static List<String> parseGenres(String genres) {
		// TODO Auto-generated method stub
		List <String> genreList= new ArrayList <String>();
		String[] genreArray = genres.split(",");
		for(int i=0;i<genreArray.length;i++)
		{
			genreList.add(genreArray[i]);
		}
		return genreList;
	}

	public static Float convertImdbRating(String imdbRating) {
		// TODO Auto-generated method stub
		if(imdbRating.equals("N/A"))
		{
			return (float) 0.0;
		}
		else
		{
			return Float.parseFloat((imdbRating).replace(",", "."));
		}

	}
	
	public static String printMovie(MovieDTO movie) {
		// TODO Auto-generated method stub
		if(movie == null) return "Film non trovato";
		else return movie.getName();
	}

	public static List<String> parseActors(String actors) {
		// TODO Auto-generated method stub
		List <String> actorsList= new ArrayList <String>();
		String[] actorsArray = actors.split(",");
		for(int i=0;i<actorsArray.length;i++)
		{
			actorsList.add(actorsArray[i]);
		}
		return actorsList;
		}

}
