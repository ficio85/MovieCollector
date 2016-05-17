package com.dto;

import java.util.List;

public class SearchDTO {

	boolean isSearchActor;
	boolean isSearchGenre;
	boolean isSearchDirector;
	boolean isSearchWriter;
	boolean isSearchLenght;
	boolean isSearchYear;
	boolean isSearchTagMovie;
	boolean isSearchTitle;
	boolean isSearchLabel;

	boolean andGenres;
	boolean andActors;
	boolean andLabels;

	List <String> actors;
	List <String> genres;
	List <String> directors;
	List <Integer> years;
	List <String> labels;
	String title;
	int start;
	int offset;
	int countResult;
	boolean count;
	int currPage;
	
				
	/**
	 * @return the labels
	 */
	public List<String> getLabels() {
		return labels;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	/**
	 * @return the andLabels
	 */
	public boolean isAndLabels() {
		return andLabels;
	}
	/**
	 * @param andLabels the andLabels to set
	 */
	public void setAndLabels(boolean andLabels) {
		this.andLabels = andLabels;
	}
	/**
	 * @return the isSearchLabel
	 */
	public boolean isSearchLabel() {
		return isSearchLabel;
	}
	/**
	 * @param isSearchLabel the isSearchLabel to set
	 */
	public void setSearchLabel(boolean isSearchLabel) {
		this.isSearchLabel = isSearchLabel;
	}
	public boolean isSearchTitle() {
		return isSearchTitle;
	}
	public void setSearchTitle(boolean isSearchTitle) {
		this.isSearchTitle = isSearchTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isAndActors() {
		return andActors;
	}
	public void setAndActors(boolean andActors) {
		this.andActors = andActors;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
		this.start = (currPage-1)*(this.offset)+1;
	}
	public boolean isCount() {
		return count;
	}
	public void setCount(boolean count) {
		this.count = count;
	}
	public int getCountResult() {
		return countResult;
	}
	public void setCountResult(int countResult) {
		this.countResult = countResult;
	}
	public boolean isAndGenres() {
		return andGenres;
	}
	public void setAndGenres(boolean andGenres) {
		this.andGenres = andGenres;
	}
	public List<Integer> getYears() {
		return years;
	}
	public void setYears(List<Integer> years) {
		this.years = years;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public List<String> getDirectors() {
		return directors;
	}
	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}
	public List<String> getActors() {
		return actors;
	}
	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public boolean isSearchActor() {
		return isSearchActor;
	}
	public void setSearchActor(boolean isSearchActor) {
		this.isSearchActor = isSearchActor;
	}
	public boolean isSearchWriter() {
		return isSearchWriter;
	}
	public void setSearchWriter(boolean isSearchWriter) {
		this.isSearchWriter = isSearchWriter;
	}

	public boolean isSearchGenre() {
		return isSearchGenre;
	}
	public void setSearchGenre(boolean isSearchGenre) {
		this.isSearchGenre = isSearchGenre;
	}
	public boolean isSearchDirector() {
		return isSearchDirector;
	}
	public void setSearchDirector(boolean isSearchDirector) {
		this.isSearchDirector = isSearchDirector;
	}
	public boolean isSearchLenght() {
		return isSearchLenght;
	}
	public void setSearchLenght(boolean isSearchLenght) {
		this.isSearchLenght = isSearchLenght;
	}
	public boolean isSearchYear() {
		return isSearchYear;
	}
	public void setSearchYear(boolean isSearchYear) {
		this.isSearchYear = isSearchYear;
	}
	public boolean isSearchTagMovie() {
		return isSearchTagMovie;
	}
	public void setSearchTagMovie(boolean isSearchTagMovie) {
		this.isSearchTagMovie = isSearchTagMovie;
	}





}
