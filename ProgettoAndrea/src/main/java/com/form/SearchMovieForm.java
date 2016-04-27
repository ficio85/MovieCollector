package com.form;

import java.util.List;

import com.dto.GenereDTO;

public class SearchMovieForm {


	private String actor;
	private List <GenereDTO> genereList;
	private String genere;
	private String director;
	private String minLength;
	private String maxLength;
	private int year;
	
	

	
	public String getMinLength() {
		return minLength;
	}
	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public List<GenereDTO> getGenereList() {
		return genereList;
	}
	public void setGenereList(List<GenereDTO> genereList) {
		this.genereList = genereList;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}



}
