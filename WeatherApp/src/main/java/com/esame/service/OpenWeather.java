package com.esame.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.esame.model.City;

public class OpenWeather {
	
	private ArrayList<City> arrayCities = new ArrayList<>();
	
	/**
	 * 
	 * @param box di coordinate
	 * @return jsonarray contenente info attuali su città interne al box
	 */
	public JSONArray downloadArray(String box) {
		JSONArray jsonArrayCities = new JSONArray();
		String API_KEY = "06a4865d9759cde0491b4e2fccc9f266";
		String COORDINATES = box;
		String urlString = "http://api.openweathermap.org/data/2.5/box/city?bbox=" + COORDINATES
				+ "&appid=" + API_KEY;
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			JSONObject obj = (JSONObject) JSONValue.parseWithException(result.toString());
			JSONArray obj_Array = (JSONArray) obj.get("list");
			for(Object o : obj_Array) {
				if (o instanceof JSONObject) {
			    	JSONObject o1 = (JSONObject)o;
			    	String name = (String)o1.get("name");
			    	long date = Long.parseLong(o1.get("dt").toString());
			    	JSONObject o2 = (JSONObject)o1.get("wind");
			    	double speed = Double.parseDouble(o2.get("speed").toString());
			    	int deg = Integer.parseInt(o2.get("deg").toString());
			    	JSONObject o3 = (JSONObject)o1.get("clouds");
			    	double clouds = Double.parseDouble(o3.get("today").toString());
			    	City city = new City(name, speed, deg, clouds, date);
			    	arrayCities.add(city);
			    	jsonArrayCities.add(city.getJsonObject());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArrayCities;
			
	}
	
	
	
	
	
}
