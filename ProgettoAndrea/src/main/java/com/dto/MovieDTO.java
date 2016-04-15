package com.dto;

import java.util.List;

public class MovieDTO {
	
	String name;
	int length;
	String title;
	String titoloItaliano;
	float imdbRating;
	float metaCritic;
	int year;
	List <String> actors;
	String plot;
	List <String> genre;
	List <String> countries;
	String movieKey;
	int numImdbRating;
	String director;
	String writer;
	
	
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getNumImdbRating() {
		return numImdbRating;
	}
	public void setNumImdbRating(int numImdbRating) {
		this.numImdbRating = numImdbRating;
	}
	public float getMetaCritic() {
		return metaCritic;
	}
	public void setMetaCritic(float metaCritic) {
		this.metaCritic = metaCritic;
	}
	public String getTitoloItaliano() {
		return titoloItaliano;
	}
	public void setTitoloItaliano(String titoloItaliano) {
		this.titoloItaliano = titoloItaliano;
	}
	public String getMovieKey() {
		return movieKey;
	}
	public void setMovieKey(String movieKey) {
		this.movieKey = movieKey;
	}
	public List<String> getCountries() {
		return countries;
	}
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(float imdbRating) {
		this.imdbRating = imdbRating;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<String> getActors() {
		return actors;
	}
	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public List<String> getGenre() {
		return genre;
	}
	public void setGenre(List<String> genre) {
		this.genre = genre;
	}
	



}
