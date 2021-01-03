package com.esame.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.esame.filter.FilterByName;
import com.esame.filter.FilterByPeriod;
import com.esame.model.City;
import com.esame.model.Stats;
import com.esame.model.StatsObject;
import com.esame.stats.StatsClouds;
import com.esame.stats.StatsSpeed;

@Service
public class OpenWeatherService {
	
	/**
	 * metodo che esegue chiamata all'API e jsonparsing
	 * @param box di coordinate
	 * @return JSONarray contenente info attuali su città interne al box
	 */
	@SuppressWarnings("unchecked")
	public JSONArray actualDataService(String box) {
		String result = OpenWeatherUtils.API_Call(box);
		JSONArray jsonArrayCities = new JSONArray();
		try {
			JSONObject obj = (JSONObject) JSONValue.parseWithException(result);
			JSONArray obj_Array = (JSONArray) obj.get("list");
			for(Object o : obj_Array) {
				if (o instanceof JSONObject) {
			    	JSONObject o1 = (JSONObject)o;
			    	String name = (String)o1.get("name");
			    	long dateInEpoch = Long.parseLong(o1.get("dt").toString());
			    	LocalDateTime date = LocalDateTime.ofEpochSecond(dateInEpoch, 0, OffsetDateTime.now().getOffset());
			    	JSONObject o2 = (JSONObject)o1.get("wind");
			    	double speed = Double.parseDouble(o2.get("speed").toString());
			    	int deg = Integer.parseInt(o2.get("deg").toString());
			    	JSONObject o3 = (JSONObject)o1.get("clouds");
			    	double clouds = Double.parseDouble(o3.get("today").toString());
			    	City city = new City(name, speed, deg, clouds, date);
			    	jsonArrayCities.add(city.getJsonObject());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArrayCities;
			
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray statsService(String type, int period) {
		JSONArray jsonArrayStatsObjects = new JSONArray();
		ArrayList<City> arrayCities = OpenWeatherUtils.readCSV();
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();

		FilterByPeriod filterByPeriod = new FilterByPeriod(arrayCities, period);
		while(!filterByPeriod.getArrayCities().isEmpty() && filterByPeriod.getArrayCities() != null) {
			arrayCitiesFiltered = filterByPeriod.filter();
			String periodOfDatas = "da " + arrayCitiesFiltered.get(0).getDate().toString() + " a " + 
			                      arrayCitiesFiltered.get(arrayCitiesFiltered.size()-1).getDate().toString();
			FilterByName filterByName = new FilterByName(arrayCitiesFiltered);
			ArrayList<Stats> arrayStats = new ArrayList<>();
			switch (type) {
			case "Clouds" :
			case "clouds" :
				while(!filterByName.getArrayCities().isEmpty() && filterByName.getArrayCities() != null) {
					arrayCitiesFiltered = filterByName.filter();
					StatsClouds statsClouds = new StatsClouds(arrayCitiesFiltered);
					Stats stats = statsClouds.calculate();
					stats.setName(arrayCitiesFiltered.get(0).getName());
					arrayStats.add(stats);
				}
				StatsObject statsObject = new StatsObject(arrayStats, "nuvolosità", periodOfDatas);
				jsonArrayStatsObjects.add(statsObject.getJsonObject());
				break;
			case "Wind":
			case "wind":
				while(!filterByName.getArrayCities().isEmpty() && filterByName.getArrayCities() != null) {
					arrayCitiesFiltered = filterByName.filter();
					StatsSpeed statsSpeed = new StatsSpeed(arrayCitiesFiltered);
					Stats stats = statsSpeed.calculate();
					stats.setName(arrayCitiesFiltered.get(0).getName());
					arrayStats.add(stats);
				}
				StatsObject statsObject2 = new StatsObject(arrayStats, "velocità del vento", periodOfDatas);
				jsonArrayStatsObjects.add(statsObject2.getJsonObject());
				break;
			}
			
		}
		return jsonArrayStatsObjects;
	}
	
	
}
