package com.esame.model;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;

public class City implements Comparable<City> {
	private String name;
	private double speed;
	private int deg;
	private double clouds;
	private LocalDateTime date;
	
	/**
	 * costruttore
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
	
	@Override
	public String toString() {
		return (name + ',' + speed + ',' + deg + ',' + clouds + ',' + date + '\n');
	}

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
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * @return the deg
	 */
	public int getDeg() {
		return deg;
	}

	/**
	 * @param deg the deg to set
	 */
	public void setDeg(int deg) {
		this.deg = deg;
	}

	/**
	 * @return the clouds
	 */
	public double getClouds() {
		return clouds;
	}

	/**
	 * @param clouds the clouds to set
	 */
	public void setClouds(double clouds) {
		this.clouds = clouds;
	}

	/**
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
