package com.esame.model;

/**
 * Classe Stats
 * @author Riccardo Vico
 * @author Riccardo Malpiedi
 */
public class Stats {
	
	/**
	 * nome della citta'
	 */
	private String name;
	
	/**
	 * media
	 */
	private double average;
	
	/**
	 * varianza
	 */
	private double variance;
	
	/**
	 * Costruttore classe Stats
	 * @param name
	 * @param average
	 * @param variance
	 */
	public Stats(String name, double average, double variance) {
		this.name = name;
		this.average = average;
		this.variance = variance;
	}
	
	/**
	 * Costruttore della classe stats
	 */
	public Stats() {}
	
	/**
	 * Getter del nome della citta'
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter del nome della citta'
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getter della media
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}
	/**
	 * Setter della media
	 * @param average the average to set
	 */
	public void setAverage(double average) {
		this.average = average;
	}
	/**
	 * Getter della varianza
	 * @return the variance
	 */
	public double getVariance() {
		return variance;
	}
	/**
	 * Setter della varianza
	 * @param variance the variance to set
	 */
	public void setVariance(double variance) {
		this.variance = variance;
	}
	
}
