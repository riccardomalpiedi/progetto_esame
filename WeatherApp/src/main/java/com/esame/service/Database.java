package com.esame.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.esame.model.City;

@Component
public class Database { 
	private final String box = "13,43,14,44,10";
	
	@Scheduled(fixedRateString = "PT2H")
	public void download() {
		ArrayList<City> arrayCities = new ArrayList<>();
		String result = OpenWeather.downloadJSON(box);
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File("prova.csv");
		try {
			if(!file.exists()) 
				file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(file.getAbsolutePath());
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("prova.csv", true));
			for(int i = 0; i < arrayCities.size(); i++) {
				bufferedWriter.write(arrayCities.get(i).toString());
			}
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
