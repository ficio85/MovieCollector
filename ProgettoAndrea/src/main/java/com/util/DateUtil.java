package com.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Timestamp setDateToday(int hours, int minutes)
	{
		java.util.Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, hours);
		calendar.set(Calendar.MINUTE, minutes);
		return new Timestamp(calendar.getTimeInMillis());
	}

}
