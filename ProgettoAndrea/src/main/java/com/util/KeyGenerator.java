package com.util;
import java.util.UUID;

import org.apache.commons.lang.math.RandomUtils;

import com.dto.MovieDTO;

public class KeyGenerator {
	
	
	public static String movieKeyGeneratorUtil(MovieDTO movie){
		String randomString=UUID.randomUUID().toString().substring(0, 5).toUpperCase();
		char[] movieCharArray = movie.getTitle().trim().replace(" ", "").toCharArray();
		String movieNameKeyArray="";
		for(int i=0;i<8;i++){
			if((movieCharArray.length )>i)
			{
				movieNameKeyArray+=movieCharArray[i];
			}
			else
			{
				movieNameKeyArray+="A";
			}
			
		}
		
		String key="MV"+randomString+""+movieNameKeyArray.toUpperCase()+movie.getYear()+"Z";
		return key;
		
	}

}
