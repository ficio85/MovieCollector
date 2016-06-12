package com.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import com.dto.ProgramMovieDTO;

public class ProgramTvMovieDTO extends ProgramMovieDTO {
	
	public ProgramTvMovieDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Timestamp oraInizio;

	public Timestamp getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(Timestamp oraInizio) {
		this.oraInizio = oraInizio;
	}
	
	public String getDisplayHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(oraInizio);
		return cal.get(Calendar.HOUR_OF_DAY)+"."+cal.get(Calendar.MINUTE)+"";
	}
	

}
