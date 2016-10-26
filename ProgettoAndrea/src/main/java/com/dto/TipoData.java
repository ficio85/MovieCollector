package com.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TipoData {

	int day;
	int month;
	int year;
	int hour;
	int minute;
	
	public TipoData(Date date)
	{
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		day=cal.get(Calendar.DAY_OF_MONTH);
		month=cal.get(Calendar.MONTH);
		year=cal.get(Calendar.YEAR);
	}
	
	public TipoData(Timestamp date)
	{
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		day=cal.get(Calendar.DAY_OF_MONTH);
		month=cal.get(Calendar.MONTH);
		year=cal.get(Calendar.YEAR);
		hour=cal.get(Calendar.HOUR_OF_DAY);
		minute=cal.get(Calendar.MINUTE);
	}
	
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}
	/**
	 * @param minute the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public java.sql.Date getAsSQLDate() {

		try {
			String data = day + "/" + month + "/" + year;
			java.util.Date out = null;
			java.sql.Date dateOut = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			sdf.setTimeZone(TimeZone.getTimeZone ("GMT"));
			if (data != null) {
				out = sdf.parse(data);
				dateOut = new java.sql.Date(out.getTime());
			}
			return dateOut;
		} catch (Exception e) {
			return null;
		}
	}
	
}
