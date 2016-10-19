package com.dto;

import java.sql.Timestamp;

public class UserActorRateDTO {

	String actor;
	String user;
	Timestamp time;
	float rate;
	float autorate;
	/**
	 * @return the actor
	 */
	public String getActor() {
		return actor;
	}
	/**
	 * @param actor the actor to set
	 */
	public void setActor(String actor) {
		this.actor = actor;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the time
	 */
	public Timestamp getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Timestamp time) {
		this.time = time;
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
	/**
	 * @return the autorate
	 */
	public float getAutorate() {
		return autorate;
	}
	/**
	 * @param autorate the autorate to set
	 */
	public void setAutorate(float autorate) {
		this.autorate = autorate;
	}
	
	
	
}
