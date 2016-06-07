package com.dto;

import java.util.List;

public class ActorDTO {

	String name;
	String surname;
	String role;
	String image;
	int age;
	int indexList;
	String imdbIndex;
	float rate;
	List <LabelDTO> labels;
	
	
	
	
	
	
	
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getImdbIndex() {
		return imdbIndex;
	}
	public void setImdbIndex(String imdbIndex) {
		this.imdbIndex = imdbIndex;
	}
	public int getIndexList() {
		return indexList;
	}
	public void setIndexList(int indexList) {
		this.indexList = indexList;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	
}
