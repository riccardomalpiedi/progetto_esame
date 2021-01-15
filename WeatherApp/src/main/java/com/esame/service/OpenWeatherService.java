package com.esame.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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


import com.esame.filter.FilterByPeriod;
import com.esame.filter.FilterDaily;
import com.esame.filter.FilterWeekly;
import com.esame.model.City;

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
	
	public JSONArray statsService(String type) {
		ArrayList<City> arrayCities = OpenWeatherUtils.readCSV();
		String periodOfDatas = "da " + arrayCities.get(0).getDate().toString() + " a " + 
                arrayCities.get(arrayCities.size()-1).getDate().toString();
		return OpenWeatherUtils.statsUtil(arrayCities, type, periodOfDatas);
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray periodicalStatsService(String type, int period) {
		JSONArray jsonArrayStatsObjects = new JSONArray();
		ArrayList<City> arrayCities = OpenWeatherUtils.readCSV();
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();

		FilterByPeriod filterByPeriod = new FilterByPeriod(arrayCities, period);
		while(!filterByPeriod.getArrayCities().isEmpty() && filterByPeriod.getArrayCities() != null) {
			arrayCitiesFiltered = filterByPeriod.filter();
			String periodOfDatas = "da " + arrayCitiesFiltered.get(0).getDate().toString() + " a " + 
			                      arrayCitiesFiltered.get(arrayCitiesFiltered.size()-1).getDate().toString();
			jsonArrayStatsObjects.add(OpenWeatherUtils.statsUtil(arrayCitiesFiltered, type, periodOfDatas));
		}
		return jsonArrayStatsObjects;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray weeklyStatsService(String type) {
		JSONArray jsonArrayStatsObjects = new JSONArray();
		ArrayList<City> arrayCities = OpenWeatherUtils.readCSV();
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();

		FilterWeekly filterWeekly = new FilterWeekly(arrayCities);
		while(!filterWeekly.getArrayCities().isEmpty() && filterWeekly.getArrayCities() != null) {
			arrayCitiesFiltered = filterWeekly.filter();
			String periodOfDatas = "da " + arrayCitiesFiltered.get(0).getDate().toString() + " a " + 
			                      arrayCitiesFiltered.get(arrayCitiesFiltered.size()-1).getDate().toString();
			jsonArrayStatsObjects.add(OpenWeatherUtils.statsUtil(arrayCitiesFiltered, type, periodOfDatas));
		}
		return jsonArrayStatsObjects;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray dailyStatsService(String type) {
		JSONArray jsonArrayStatsObjects = new JSONArray();
		ArrayList<City> arrayCities = OpenWeatherUtils.readCSV();
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();

		FilterDaily filterDaily = new FilterDaily(arrayCities);
		while(!filterDaily.getArrayCities().isEmpty() && filterDaily.getArrayCities() != null) {
			arrayCitiesFiltered = filterDaily.filter();
			String periodOfDatas = "da " + arrayCitiesFiltered.get(0).getDate().toString() + " a " + 
			                      arrayCitiesFiltered.get(arrayCitiesFiltered.size()-1).getDate().toString();
			jsonArrayStatsObjects.add(OpenWeatherUtils.statsUtil(arrayCitiesFiltered, type, periodOfDatas));
		}
		return jsonArrayStatsObjects;
	}
	
	public String changeBoxService(String box) {
		
		String API_KEY = "06a4865d9759cde0491b4e2fccc9f266";
		String COORDINATES = box;
		String urlString = "http://api.openweathermap.org/data/2.5/box/city?bbox=" + COORDINATES
				+ "&appid=" + API_KEY;
		String line = null;
		
		try {
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			line = rd.readLine();
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			JSONObject obj = (JSONObject) JSONValue.parseWithException(line);
			if(obj.get("cod").toString() == "200") {
				
				File file = new File("box.txt");
				try {
					if(!file.exists())
						file.createNewFile();
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				try {
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("box.txt"));
					bufferedWriter.write(box);
					bufferedWriter.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
				return "si";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "no no";
	}
	
}
