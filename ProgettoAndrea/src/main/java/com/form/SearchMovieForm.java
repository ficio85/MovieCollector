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
	private String year;
	private String label;
	
	

	
	
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
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


}
