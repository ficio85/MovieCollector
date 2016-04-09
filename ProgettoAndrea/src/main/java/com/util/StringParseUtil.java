package com.util;

public class StringParseUtil {

	
	public static int fromStringLengthToInt(String stringLength)
	{	
		if(!stringLength.trim().equals(""))
			return 0;
		else
		return Integer.parseInt(stringLength.replaceAll("\\D+",""));

	}
}
