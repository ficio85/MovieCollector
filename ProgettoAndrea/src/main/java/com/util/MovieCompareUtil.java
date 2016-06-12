package com.util;

import java.util.List;


import com.dto.ActorDTO;
import com.dto.MovieDTO;

public class MovieCompareUtil {
	
	
	private static boolean isSameActor (ActorDTO actor1, ActorDTO actor2)
	{
		if(actor1.getName().trim().equals(actor2.getName().trim()))
		{
			return true;
		}
		else
		{
			return false;
		}	
			
	}
	
	public static int numberSameActors(MovieDTO movie1, MovieDTO movie2)
	{
		List <ActorDTO> actorsList1= movie1.getActors();
		List <ActorDTO> actorsList2= movie2.getActors();
		int counter=0;
		for (int i=0;i<actorsList1.size();i++)
		{
			for(int j=0; j< actorsList2.size();j++)
			{
				if(isSameActor(actorsList1.get(i),actorsList2.get(j)))
				{
					counter++;
				}
			}
		}
		return counter;
		
	}

}
