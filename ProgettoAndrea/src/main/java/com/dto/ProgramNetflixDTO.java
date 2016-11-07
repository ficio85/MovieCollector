package com.dto;

import java.util.Date;

public class ProgramNetflixDTO {
	
	String title;
	MovieDTO movie;
	String type;
	TipoData release;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the movie
	 */

	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the movie
	 */
	public MovieDTO getMovie() {
		return movie;
	}
	/**
	 * @param movie the movie to set
	 */
	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the release
	 */
	public TipoData getRelease() {
		return release;
	}
	/**
	 * @param release the release to set
	 */
	public void setRelease(TipoData release) {
		this.release = release;
	}
	
	

}
