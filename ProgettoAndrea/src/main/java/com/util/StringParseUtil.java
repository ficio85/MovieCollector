package com.util;

public class StringParseUtil {

	
	public static int fromStringLengthToInt(String stringLength)
	{	
		if(!MovieGeneratorUtil.isNotNullEntry(stringLength))
			return 0;
		else
		return Integer.parseInt(stringLength.replaceAll("\\D+",""));

	}
}
