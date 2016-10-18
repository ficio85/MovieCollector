package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.dto.ActorDTO;
import com.dto.DirectorDTO;

public class DirectorGeneratorUtil {

	
	
	public static void getCompleteInfoDirector(DirectorDTO director) throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		JsoupUtil.generateImdbDirectorInfo(director);
		JsoupUtil.generateWikiDirectorInfo(director);
		
	}
	
	public static List <String> getDirectorsString(List<DirectorDTO> directors,int size)
	{
		List <String> directorString = new ArrayList <String>();
		int i=0;

		for(DirectorDTO director: directors)
		{
			if(i<size)
			{
				directorString.add(director.getName());
				i++;
			}
		}
		return directorString;
		
	}
}
