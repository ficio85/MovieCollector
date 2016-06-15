package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.dto.ActorDTO;
import com.dto.DirectorDTO;

public class DirectorGeneratorUtil {

	
	
	public static void getCompleteInfoDirector(DirectorDTO director) throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		JsoupUtil.generateImdbDirectorInfo(director);
		JsoupUtil.generateWikiDirectorInfo(director);
		
	}
}
