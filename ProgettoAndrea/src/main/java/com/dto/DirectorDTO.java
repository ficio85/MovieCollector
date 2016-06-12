package com.dto;

public class DirectorDTO {

	String name;
	String surname;
	int indexList;
	
	
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


}
