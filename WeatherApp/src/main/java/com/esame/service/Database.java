package com.esame.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.esame.model.City;

@Component
public class Database { 
	
	/**
	 * 
	 * metodo che esegue chiamata all'API ogni due ore e salva i dati utili in un file CSV
	 */
	@Scheduled(fixedRateString = "PT2H")
	public void download() {
		String box = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("box.txt"));
			box = bufferedReader.readLine();
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ArrayList<City> arrayCities = new ArrayList<>();
		String result = OpenWeatherUtils.API_Call(box);
		try {
			JSONObject obj = (JSONObject) JSONValue.parseWithException(result);
			JSONArray obj_Array = (JSONArray) obj.get("list");
			for(Object o : obj_Array) {
				if (o instanceof JSONObject) {
			    	JSONObject o1 = (JSONObject)o;
			    	String name = (String)o1.get("name");
			    	long dateInEpoch = Long.parseLong(o1.get("dt").toString());
			    	LocalDateTime date = LocalDateTime.ofEpochSecond(dateInEpoch,
			    			                                   0, OffsetDateTime.now().getOffset());
			    	JSONObject o2 = (JSONObject)o1.get("wind");
			    	double speed = Double.parseDouble(o2.get("speed").toString());
			    	int deg = Integer.parseInt(o2.get("deg").toString());
			    	JSONObject o3 = (JSONObject)o1.get("clouds");
			    	double clouds = Double.parseDouble(o3.get("today").toString());
			    	City city = new City(name, speed, deg, clouds, date);
			    	arrayCities.add(city);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Collections.sort(arrayCities);
		File file = new File("storico.csv");
		try {
			if(!file.exists()) 
				file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("storico.csv", true));
			for(int i = 0; i < arrayCities.size(); i++) {
				bufferedWriter.write(arrayCities.get(i).toString());
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
