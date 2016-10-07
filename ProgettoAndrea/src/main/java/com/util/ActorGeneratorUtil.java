package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.dto.ActorDTO;

public class ActorGeneratorUtil {

	public static void getCompleteInfoActor(ActorDTO actor) throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		JsoupUtil.generateImdbActorInfo(actor);
		JsoupUtil.generateWikiActorInfo(actor);
		
	}
	
	public static List <String> getActorsString(List<ActorDTO> actors,int size)
	{
		List <String> actorString = new ArrayList <String>();
		int i=0;

		for(ActorDTO actor: actors)
		{
			if(i<size)
			{
				actorString.add(actor.getName());
				i++;
			}
		}
		return actorString;
		
	}
	
	
}
