package com.dto;

import java.sql.Date;
import java.util.List;

public class ActorDTO {

	String name;
	String surname;
	String fullname;
	String bithplace;
	String role;
	List <ImageDTO> images;
	int age;
	int indexList;
	String imdbIndex;
	float rate;
	List <LabelDTO> labels;
	Date birthDate; 
	
	
	
	public String getFullname() {
		return fullname;
	}



	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public String getBithplace() {
		return bithplace;
	}



	public void setBithplace(String bithplace) {
		this.bithplace = bithplace;
	}



	public Date getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	public ActorDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ActorDTO(String ownText) {
this.name = ownText;
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



	public List<ImageDTO> getImages() {
		return images;
	}



	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
	
	
}
