package com.dto;

public class ProgramMovieDTO {
	
	String platform;
	MovieDTO movie;
	protected ProgramMovieDTO(){
		movie = new MovieDTO();
	}
	
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public MovieDTO getMovie() {
		return movie;
	}
	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}
	
	
	

}
