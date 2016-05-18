package com.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dto.ActorDTO;
import com.dto.LabelDTO;
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

	public static Float convertMetascore(String metascore) {
		// TODO Auto-generated method stub
		if(metascore.equals("N/A"))
		{
			return (float) 0.0;
		}
		else
		{
			return Float.parseFloat((metascore).replace(",", "."));
		}

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
	public static Float convertNumImdbRating(String imdbRating) {
		// TODO Auto-generated method stub
		if(imdbRating.equals("N/A"))
		{
			return (float) 0.0;
		}
		else
		{
			return Float.parseFloat((imdbRating).replace(",", ""));
		}

	}
	
	
	
	public static String printMovie(MovieDTO movie) {
		// TODO Auto-generated method stub
		if(movie == null) return "Film non trovato";
		else return movie.getTitle();
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

	public static List<String> parseWriters(String writers) {
		// TODO Auto-generated method stub
		List <String> writersList= new ArrayList <String>();
		String[] writersArray = writers.split(",");
		for(int i=0;i<writersArray.length;i++)
		{
			String writername= writersArray[i].split("\\(")[0].trim();
			writersList.add(writername);
		}
		return writersList;
	}
	
	public static List<String> parseWorkWriters(String writers) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		List <String> workList= new ArrayList <String>();
		String[] writersArray = writers.split(",");
		for(int i=0;i<writersArray.length;i++)
		{
			String[] work =writersArray[i].split("\\(");
			if(work.length>1)
			{
				String referringWork=work[1].split("\\)")[0];
				workList.add(referringWork);
			}
			else
			{
				workList.add(" ");
			}
		}
		return workList;
	
	}
	
	public static List<String> parseDirectors(String directors) {
		// TODO Auto-generated method stub
		List <String> directorsList= new ArrayList <String>();
		String[] directorsArray = directors.split(",");
		for(int i=0;i<directorsArray.length;i++)
		{
			directorsList.add(directorsArray[i]);
		}
		return directorsList;
	}

	public static String convertMonth(String month) {

		switch(month)
		{
		case   "JAN": return "01";
		case   "FEB": return "02";
		case   "MAR":return "03";
		case   "APR":return "04";
		case   "MAY":return "05";
		case   "JUN":return "06";
		case   "JUL":return "07";
		case   "AUG":return "08";
		case   "SEP":return "09";
		case   "OCT":return "10";
		case   "NOV":return "11";
		case   "DEC":return "12";



		}
		
		
		return null;
	}

	public static String parseMovieTitle(String title) {
		// TODO Auto-generated method stub
		return title.replace(" ", "+");
	}

	public static boolean isNotNullEntry(String entry) {
		// TODO Auto-generated method stub
		if(entry!=null && !entry.trim().equals("") && !entry.trim().equals("N/A"))
		{
			return true;
		}
		else return false;
	}
	public static List<String> parseLanguages(String string) {
		// TODO Auto-generated method stub
		List <String> languagesList= new ArrayList <String>();
		String[] languagesArray = string.split(",");
		for(int i=0;i<languagesArray.length;i++)
		{
			languagesList.add(languagesArray[i]);
		}
		return languagesList;
	}

	public static String convertTitleToString(Object object) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String out= convertToUTF8((String) object);
	    return new String((byte[]) object, "UTF8");
	}

	 public static String convertToUTF8(String s) {
	        String out = null;
	        try {
	            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
	        } catch (java.io.UnsupportedEncodingException e) {
	            return null;
	        }
	        return out;
	    }

	public static int parseEpisode(String episode) {
		// TODO Auto-generated method stub
		if(episode!=null && !episode.trim().equals("") && !episode.trim().equals("N/A"))
		{
			return Integer.parseInt(episode);
		}
		else
		{
			return 0;
		}
	}

	public static int parseSeason(String season) {
		// TODO Auto-generated method stub
		if(season!=null && !season.trim().equals("") && !season.trim().equals("N/A"))
		{
			return Integer.parseInt(season);
		}
		else
		{
			return 0;
		}
		
	}

	public static ArrayList <LabelDTO> getLabels(String[] parameterValues) {
		// TODO Auto-generated method stub
		ArrayList <LabelDTO> labels= new ArrayList<LabelDTO>();
		for(String parameter: parameterValues)
		{
			LabelDTO label = new LabelDTO();
			label.setName(parameter);
			labels.add(label);
		}
		return labels;
	}
	
	public static String getLabelRandomClass() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		String render="";
		int randint =rand.nextInt(6);
		switch (randint) { 
		case 1: 
			render= "label-primary ";break;
		case 2: 
			render= "label-success";	break;    			
		case 3: 
			render= "label-info";break;	    					
		case 4: 
			render= "label-warning";break;
		case 5: 
			render= "label-danger";break;
		default: render= "label-default";
		}
		return render;
	}

	public static String getStarClass(float rank) {
		// TODO Auto-generated method stub
		String render="";
		
		if(rank<3)
		{
			render+="star-black-render ";
		}
		else if ( rank>=3 && rank<6)
		{
			render+="star-red-render ";
		}
		else if(rank>=6 && rank <8)
		{
			render+="star-green-render ";
		}
		else if(rank>=8)
		{
			render+="star-yellow-render ";
		}
		return render;
		
	}
	


}
