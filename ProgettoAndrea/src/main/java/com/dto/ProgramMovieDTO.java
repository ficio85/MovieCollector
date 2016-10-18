package com.dto;

public class ProgramMovieDTO {
	
	String platform;
	String channel;
	MovieDTO movie;
	String tipo;
	String titolo;
	
	protected ProgramMovieDTO(){
		movie = new MovieDTO();
	}
	

	
	
	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}




	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}




	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}




	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}




	public MovieDTO getMovie() {
		return movie;
	}
	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * @param titolo the titolo to set
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	
	
	

}
