package com.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Timestamp setDateToday(int hours, int minutes)
	{
		java.util.Date date = new Date();
		Calendar calendar = getCalendarHour(hours, minutes);
		return new Timestamp(calendar.getTimeInMillis());
	}

	public static Calendar getCalendarHour(int hours, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		return calendar;
	}
	
	public static Timestamp addMinutes(Timestamp time, int minutes)
	{
		return new Timestamp(time.getTime()+1000*60*minutes);
	}
	
	public static Timestamp subMinutes(Timestamp time, int minutes)
	{
		return new Timestamp(time.getTime()-1000*60*minutes);
	}
	

}
