package com.jsonResponse;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class RateResponse {

	boolean rateChanged;
	float newRate;
	float oldRate;
	String newRateString;
	
	
	
	
	/**
	 * @return the newRateString
	 */
	public String getNewRateString() {
		return newRateString;
	}
	/**
	 * @param newRateString the newRateString to set
	 */
	public void setNewRateString(float newRate) {
		DecimalFormat df = new DecimalFormat("##.#");
		df.setRoundingMode(RoundingMode.CEILING);
		this.newRateString =(df.format(newRate));
	}
	/**
	 * @return the oldRate
	 */
	public float getOldRate() {
		return oldRate;
	}
	/**
	 * @param oldRate the oldRate to set
	 */
	public void setOldRate(float oldRate) {
		this.oldRate = oldRate;
	}
	/**
	 * @return the rateChanged
	 */
	public boolean isRateChanged() {
		return rateChanged;
	}
	/**
	 * @param rateChanged the rateChanged to set
	 */
	public void setRateChanged(boolean rateChanged) {
		this.rateChanged = rateChanged;
	}
	/**
	 * @return the newRate
	 */
	public float getNewRate() {
		return newRate;
	}
	/**
	 * @param newRate the newRate to set
	 */
	public void setNewRate(float newRate) {
		this.newRate = newRate;
	}
	
	
	
}
