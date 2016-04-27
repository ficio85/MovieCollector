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
	
	List <String> actors;
	List <String> genres;
	List <String> directors;
	
	
	
	
	
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
