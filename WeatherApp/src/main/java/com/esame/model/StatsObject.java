package com.esame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.simple.JSONObject;

public class StatsObject {
	
	private ArrayList<Stats> arrayStats;
	private String maxCity;
	private String minCity;
	private String maxVarianceCity;
	private String statsType;
	private String period;
	
	public StatsObject(ArrayList<Stats> arrayStats, String statsType, String period) {
		this.arrayStats = arrayStats;
		this.statsType = statsType;
		this.period = period;
		Comparator<Stats> comparatorAverage = new Comparator<Stats>() {
			public int compare(Stats s1, Stats s2) {
	            return (int) (s1.getAverage() - s2.getAverage());
	        }
	    };
	    maxCity = Collections.max(arrayStats, comparatorAverage).getName();
	    minCity = Collections.min(arrayStats, comparatorAverage).getName();
	    Comparator<Stats> comparatorVariance = new Comparator<Stats>() {
			public int compare(Stats s1, Stats s2) {
	            return (int) (s1.getVariance() - s2.getVariance());
	        }
	    };
	    maxVarianceCity = Collections.max(arrayStats, comparatorVariance).getName();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("Statistiche", arrayStats);
		obj.put("Città con media più alta", maxCity);
		obj.put("Città con media più bassa", minCity);
		obj.put("Città con varianza massima", maxVarianceCity);
		obj.put("tipo", statsType);
		obj.put("periodo", period);
		return obj;
	}
	
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
		return statsType;
	}
	/**
	 * @param statsType the statsType to set
	 */
	public void setStatsType(String statsType) {
		this.statsType = statsType;
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
