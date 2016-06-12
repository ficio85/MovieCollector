package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.dto.ActorDTO;

public class ActorGeneratorUtil {

	public static void getCompleteInfoActor(ActorDTO actor) throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		JsoupUtil.generateImdbActorInfo(actor);
		JsoupUtil.generateWikiActorInfo(actor);
		
	}
	
	
}
