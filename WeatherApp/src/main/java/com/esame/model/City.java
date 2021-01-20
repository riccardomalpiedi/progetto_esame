package com.esame.model;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;

/**
 * Classe City
 * @author Riccardo Malpiedi
 */
public class City implements Comparable<City> {
	
	/**
	 * Nome della citta'
	 */
	private String name;
	
	/**
	 * Velocita' del vento
	 */
	private double speed;
	
	/**
	 * Angolazione del vento
	 */
	private int deg;
	
	/**
	 * Nuvolosita'
	 */
	private double clouds;
	
	/**
	 * Data a cui risalgono i valori
	 */
	private LocalDateTime date;
	
	/**
	 * Costruttore della classe City
	 * 
	 * @param name nome
	 * @param speed velocita'
	 * @param deg angolazione del vento
	 * @param clouds nuvolosita'
	 * @param date date
	 */
	public City(String name, double speed, int deg, double clouds, LocalDateTime date) {
		this.name = name;
		this.speed = speed;
		this.deg = deg;
		this.clouds = clouds;
		this.date = date;
	}
	
	/**
	 * compareTo che confronta citta' in base alla data
	 * 
	 * @param city city
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
	 * @return JSONObject
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
	 * @param name nome
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter della velocita' del vento
	 * @return speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Setter della velocita' del vento
	 * @param speed velocita'
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
	 * @param deg angolazione del vento
	 */
	public void setDeg(int deg) {
		this.deg = deg;
	}

	/**
	 * Getter della nuvolosita'
	 * @return clouds
	 */
	public double getClouds() {
		return clouds;
	}

	/**
	 * Setter della nuvolosita'
	 * @param clouds nuvolosita'
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
	 * @param date data
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
