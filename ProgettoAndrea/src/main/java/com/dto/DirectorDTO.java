package com.dto;

import java.sql.Date;

public class DirectorDTO {

	String name;
	String surname;
	int indexList;
	String imdbIndex;
	String birthplace;
	Date birthDate;
	String fullname;
	
	
	
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
	
	

}
