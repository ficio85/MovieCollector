package com.dto;

import java.sql.Date;
import java.util.List;

public class MovieDTO {
	
	int length;
	Date releaseDate;
	String title;
	String titoloItaliano;
	float imdbRating;
	float metaScore;
	Float numImdbRating;
	int year;
	List <String> actors;
	String plot;
	List <String> genre;
	List <String> countries;
	String movieKey;
	String imdbKey;
	List <String> directors;
	List <String> writers;
	List <String> languages;
	String rated;
	String awards;
	String poster;
	String type;
	
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImdbKey() {
		return imdbKey;
	}
	public void setImdbKey(String imdbKey) {
		this.imdbKey = imdbKey;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public List<String> getWriters() {
		return writers;
	}
	public void setWriters(List<String> writers) {
		this.writers = writers;
	}
	public Float getNumImdbRating() {
		return numImdbRating;
	}
	public void setNumImdbRating(Float float1) {
		this.numImdbRating = float1;
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
	public List<String> getDirectors() {
		return directors;
	}
	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}
	public List<String> getGenre() {
		return genre;
	}
	public void setGenre(List<String> genre) {
		this.genre = genre;
	}
	public float getMetaScore() {
		return metaScore;
	}
	public void setMetaScore(float metaScore) {
		this.metaScore = metaScore;
	}
	
	
	



}
