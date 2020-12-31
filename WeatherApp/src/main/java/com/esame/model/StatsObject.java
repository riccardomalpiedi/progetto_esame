package com.esame.model;

import java.util.ArrayList;

public class StatsObject {
	
	private ArrayList<Stats> arrayStats;
	private String maxCity;
	private String minCity;
	private String maxVarianceCity;
	private String StatsType;
	private String period;
	
	
	/**
	 * @return the arrayStats
	 */
	public ArrayList<Stats> getArrayStats() {
		return arrayStats;
	}
	/**
	 * @param arrayStats the arrayStats to set
	 */
	public void setArrayStats(ArrayList<Stats> arrayStats) {
		this.arrayStats = arrayStats;
	}
	/**
	 * @return the maxCity
	 */
	public String getMaxCity() {
		return maxCity;
	}
	/**
	 * @param maxCity the maxCity to set
	 */
	public void setMaxCity(String maxCity) {
		this.maxCity = maxCity;
	}
	/**
	 * @return the minCity
	 */
	public String getMinCity() {
		return minCity;
	}
	/**
	 * @param minCity the minCity to set
	 */
	public void setMinCity(String minCity) {
		this.minCity = minCity;
	}
	/**
	 * @return the maxVarianceCity
	 */
	public String getMaxVarianceCity() {
		return maxVarianceCity;
	}
	/**
	 * @param maxVarianceCity the maxVarianceCity to set
	 */
	public void setMaxVarianceCity(String maxVarianceCity) {
		this.maxVarianceCity = maxVarianceCity;
	}
	/**
	 * @return the statsType
	 */
	public String getStatsType() {
		return StatsType;
	}
	/**
	 * @param statsType the statsType to set
	 */
	public void setStatsType(String statsType) {
		StatsType = statsType;
	}
	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
}
