package com.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class DirectorDTO {

	String name;
	String surname;
	int indexList;
	String imdbIndex;
	String birthplace;
	Date birthDate;
	String fullname;
	float rate;	
	List <LabelDTO> labels;
	List <ImageDTO> images;
	private int wCompleto;
	private Timestamp timewCompleto;
	private int iCompleto;
	private Timestamp timeiCompleto;
	
	
	


	
	

	/**
	 * @return the iCompleto
	 */
	public int getiCompleto() {
		return iCompleto;
	}
	/**
	 * @param iCompleto the iCompleto to set
	 */
	public void setiCompleto(int iCompleto) {
		this.iCompleto = iCompleto;
	}
	/**
	 * @return the timeiCompleto
	 */
	public Timestamp getTimeiCompleto() {
		return timeiCompleto;
	}
	/**
	 * @param timeiCompleto the timeiCompleto to set
	 */
	public void setTimeiCompleto(Timestamp timeiCompleto) {
		this.timeiCompleto = timeiCompleto;
	}
	/**
	 * @return the wCompleto
	 */
	public int getwCompleto() {
		return wCompleto;
	}
	/**
	 * @param wCompleto the wCompleto to set
	 */
	public void setwCompleto(int wCompleto) {
		this.wCompleto = wCompleto;
	}
	/**
	 * @return the timewCompleto
	 */
	public Timestamp getTimewCompleto() {
		return timewCompleto;
	}
	/**
	 * @param timewCompleto the timewCompleto to set
	 */
	public void setTimewCompleto(Timestamp timewCompleto) {
		this.timewCompleto = timewCompleto;
	}
	/**
	 * @return the images
	 */
	public List<ImageDTO> getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
	/**
	 * @return the labels
	 */
	public List<LabelDTO> getLabels() {
		return labels;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<LabelDTO> labels) {
		this.labels = labels;
	}
	public DirectorDTO(String ownText) {
		this.name= ownText;
}
	public DirectorDTO() {
		// TODO Auto-generated constructor stub
	}
	public int getIndexList() {
		return indexList;
	}
	public void setIndexList(int i) {
		this.indexList = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the imdbIndex
	 */
	public String getImdbIndex() {
		return imdbIndex;
	}
	/**
	 * @param imdbIndex the imdbIndex to set
	 */
	public void setImdbIndex(String imdbIndex) {
		this.imdbIndex = imdbIndex;
	}
	/**
	 * @return the birthplace
	 */
	public String getBirthplace() {
		return birthplace;
	}
	/**
	 * @param birthplace the birthplace to set
	 */
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}
	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	/**
	 * @return the rate
	 */
	public float getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(float rate) {
		this.rate = rate;
	}

}
