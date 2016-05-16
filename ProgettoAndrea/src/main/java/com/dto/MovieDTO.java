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
	List <ActorDTO> actors;
	String plot;
	List <GenereDTO> genre;
	List <CountryDTO> countries;
	String movieKey;
	String imdbKey;
	List <DirectorDTO> directors;
	List <WriterDTO> writers;
	List <String> workwriters;
	List <LanguageDTO> languages;
	String rated;
	String awards;
	String poster;
	String type;
	List <LabelDTO> labels;
	
	
	//valori da inserire in caso di serie tv
	int season;
	int episode;
	String imdbSerieKey;
	
	

	
	public List<LabelDTO> getLabels() {
		return labels;
	}
	public void setLabels(List<LabelDTO> labels) {
		this.labels = labels;
	}
	public List<ActorDTO> getActors() {
		return actors;
	}
	public void setActors(List<ActorDTO> actors) {
		this.actors = actors;
	}
	public List<GenereDTO> getGenre() {
		return genre;
	}
	public void setGenre(List<GenereDTO> genre) {
		this.genre = genre;
	}
	public List<DirectorDTO> getDirectors() {
		return directors;
	}
	public void setDirectors(List<DirectorDTO> directors) {
		this.directors = directors;
	}
	public List<String> getWorkwriters() {
		return workwriters;
	}
	public void setWorkwriters(List<String> workwriters) {
		this.workwriters = workwriters;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public int getEpisode() {
		return episode;
	}
	public void setEpisode(int episode) {
		this.episode = episode;
	}
	public String getImdbSerieKey() {
		return imdbSerieKey;
	}
	public void setImdbSerieKey(String imdbSerieKey) {
		this.imdbSerieKey = imdbSerieKey;
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
	
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}

	
	public float getMetaScore() {
		return metaScore;
	}
	public void setMetaScore(float metaScore) {
		this.metaScore = metaScore;
	}
	/**
	 * @return the countries
	 */
	public List<CountryDTO> getCountries() {
		return countries;
	}
	/**
	 * @param countries the countries to set
	 */
	public void setCountries(List<CountryDTO> countries) {
		this.countries = countries;
	}
	/**
	 * @return the writers
	 */
	public List<WriterDTO> getWriters() {
		return writers;
	}
	/**
	 * @param writers the writers to set
	 */
	public void setWriters(List<WriterDTO> writers) {
		this.writers = writers;
	}
	/**
	 * @return the languages
	 */
	public List<LanguageDTO> getLanguages() {
		return languages;
	}
	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(List<LanguageDTO> languages) {
		this.languages = languages;
	}
	
	
	



}
