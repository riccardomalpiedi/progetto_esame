package com.esame.model;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;

/**
 * Classe City
 * @author Riccardo Malpiedi
 */
public class City implements Comparable<City> {
	/**
	 * Nome della città
	 */
	private String name;
	
	/**
	 * Velocità del vento
	 */
	private double speed;
	
	/**
	 * Angolazione del vento
	 */
	private int deg;
	
	/**
	 * Nuvolosità
	 */
	private double clouds;
	
	/**
	 * Data a cui risalgono i valori
	 */
	private LocalDateTime date;
	
	/**
	 * Costruttore della classe City
	 * 
	 * @param name
	 * @param speed
	 * @param deg
	 * @param clouds
	 * @param date
	 */
	public City(String name, double speed, int deg, double clouds, LocalDateTime date) {
		this.name = name;
		this.speed = speed;
		this.deg = deg;
		this.clouds = clouds;
		this.date = date;
	}
	
	/**
	 * compareTo che confronta città in base alla data
	 * 
	 * @param city
	 */
	@Override
	public int compareTo(City city) {
		if (getDate().isBefore(city.getDate())) {
			return -1;
		}
		if (getDate().isAfter(city.getDate())) {
			return 1;
		}
		else
			return 0;
	}
	
	/**
	 * Metodo che restituisce il JSONObject
	 * @return obj
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		obj.put("speed", this.speed);
		obj.put("deg", this.deg);
		obj.put("clouds", this.clouds);
		obj.put("date", this.date);
		return obj;
	}
	
	/**
	 * metodo toString
	 */
	@Override
	public String toString() {
		return (name + ',' + speed + ',' + deg + ',' + clouds + ',' + date + '\n');
	}

	/**
	 * Getter del nome
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter del nome
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter della velocità del vento
	 * @return speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Setter della velocità del vento
	 * @param speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Getter dell'angolazione del vento
	 * @return deg
	 */
	public int getDeg() {
		return deg;
	}

	/**
	 * Setter dell'angolazione del vento
	 * @param deg
	 */
	public void setDeg(int deg) {
		this.deg = deg;
	}

	/**
	 * Getter della nuvolosità
	 * @return clouds
	 */
	public double getClouds() {
		return clouds;
	}

	/**
	 * Setter della nuvolosità
	 * @param clouds
	 */
	public void setClouds(double clouds) {
		this.clouds = clouds;
	}

	/**
	 * Getter della data
	 * @return date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Setter della data
	 * @param date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
