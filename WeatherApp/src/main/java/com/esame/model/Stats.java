package com.esame.model;

public class Stats {
	
	private String name;
	private double average;
	private double variance;
	
	public Stats(String name, double average, double variance) {
		this.name = name;
		this.average = average;
		this.variance = variance;
	}
	
	public Stats() {}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}
	/**
	 * @param average the average to set
	 */
	public void setAverage(double average) {
		this.average = average;
	}
	/**
	 * @return the variance
	 */
	public double getVariance() {
		return variance;
	}
	/**
	 * @param variance the variance to set
	 */
	public void setVariance(double variance) {
		this.variance = variance;
	}
	
}
