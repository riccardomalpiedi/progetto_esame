package com.esame.service;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;

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
import com.esame.stats.StatsInterface;
import com.esame.stats.StatsSpeed;

@Service
public class OpenWeather {
	
	/**
	 * metodo che esegue chiamata all'API e jsonparsing
	 * @param box di coordinate
	 * @return JSONarray contenente info attuali su città interne al box
	 */
	@SuppressWarnings("unchecked")
	public JSONArray downloadArray(String box) {
		String result = API_Call(box);
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
	public JSONArray StatsService(String type, int period) {
		JSONArray jsonArrayStatsObjects = new JSONArray();
		ArrayList<City> arrayCities = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("prova.csv"));
			String line;
			while((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				double speed = Double.parseDouble(fields[1]);
				int deg = Integer.parseInt(fields[2]);
				double clouds = Double.parseDouble(fields[3]);
				LocalDateTime date = LocalDateTime.parse(fields[4]);
				City city = new City(name, speed, deg, clouds, date);
				arrayCities.add(city);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(arrayCities);
		FilterByPeriod filterByPeriod = new FilterByPeriod(arrayCities, period);
		while(!filterByPeriod.getArrayCities().isEmpty() && filterByPeriod.getArrayCities() != null) {
			ArrayList<City> arrayCitiesFiltered = new ArrayList<>();
			arrayCitiesFiltered = filterByPeriod.filter();
			String periodOfDatas = "da " + arrayCitiesFiltered.get(0).getDate().toString() + " a " + 
			                       arrayCitiesFiltered.get(arrayCitiesFiltered.size() - 1).getDate().toString();
			FilterByName filterByName = new FilterByName(arrayCitiesFiltered);
			ArrayList<Stats> arrayStats = new ArrayList<>();
			ArrayList<City> arrayCitiesFiltered2 = new ArrayList<>();
			System.out.println(arrayCities);
			System.out.println(arrayCitiesFiltered);
			switch (type) {
			case "Clouds" :
			case "clouds" :
				while(!filterByName.getArrayCities().isEmpty() && filterByName.getArrayCities() != null) {
					arrayCitiesFiltered2 = filterByName.filter();
					StatsClouds statsClouds = new StatsClouds(arrayCitiesFiltered2);
					Stats stats = statsClouds.calculate();
					stats.setName(arrayCitiesFiltered2.get(0).getName());
					arrayStats.add(stats);
				}
				StatsObject statsObject = new StatsObject(arrayStats, "nuvolosità", periodOfDatas);
				jsonArrayStatsObjects.add(statsObject.getJsonObject());
				break;
			case "Wind":
			case "wind":
				while(!filterByName.getArrayCities().isEmpty() && filterByName.getArrayCities() != null) {
					arrayCitiesFiltered = filterByName.filter();
					StatsSpeed statsSpeed = new StatsSpeed(arrayCitiesFiltered2);
					Stats stats = statsSpeed.calculate();
					stats.setName(arrayCitiesFiltered2.get(0).getName());
					arrayStats.add(stats);
				}
				StatsObject statsObject2 = new StatsObject(arrayStats, "velocità del vento", periodOfDatas);
				jsonArrayStatsObjects.add(statsObject2.getJsonObject());
				break;
			}
			
		}
		return jsonArrayStatsObjects;
	}
	
	/**
	 * metodo static che esegue chiamata all'API e salva contenuto in una stringa
	 * @param box di coordinate
	 * @return stringa contenente il json
	 */
	public static String API_Call(String box) {
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
		return result.toString();
	}
	
	
	
	
	
}
