package com.esame.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;

import com.esame.exceptions.InvalidBoxException;
import com.esame.exceptions.InvalidTypeException;
import com.esame.filter.FilterByName;
import com.esame.model.City;
import com.esame.model.Stats;
import com.esame.model.StatsObject;
import com.esame.stats.StatsClouds;
import com.esame.stats.StatsSpeed;

/**
 * Classe Utils
 * @author Riccardo Malpiedi
 * @author Riccardo Vico
 */
public class OpenWeatherUtils {
	
	/**
	 * metodo static che esegue chiamata all'API e salva contenuto in una stringa
	 * @param box box di coordinate
	 * @return stringa contenente il json
	 * @throws InvalidBoxException 
	 */
	public static String API_Call(String box) throws InvalidBoxException {
		String API_KEY = null;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("APIKey.txt"))) {
			API_KEY = bufferedReader.readLine();
		} catch (IOException e){
			e.printStackTrace();
		}
		String COORDINATES = box;
		String urlString = "http://api.openweathermap.org/data/2.5/box/city?bbox=" + COORDINATES
				+ ",10&appid=" + API_KEY;
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
		} catch (IOException e) {
			throw new InvalidBoxException();
		}
		return result.toString();
	}
	
	/**
	 * metodo static che legge lo storico e salva i dati in un array di City
	 * @return array di City
	 */
	public static ArrayList<City> readCSV() {
		ArrayList<City> arrayCities = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("storico.csv"));
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
		} catch (IOException e) {
			e.printStackTrace();
		} 
		Collections.sort(arrayCities);
		return arrayCities;
	}
	
	/**
	 * metodo che calcola le statistiche
	 * @param arrayCities città di cui si voglioni calcolare le statistiche
	 * @param type tipo (es. nuvolosita')
	 * @param periodOfDatas periodo a cui si riferiscono i dati
	 * @return statistiche richieste
	 * @throws InvalidTypeException 
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray statsUtil(ArrayList<City> arrayCities, String type, String periodOfDatas) throws InvalidTypeException {
		JSONArray jsonArrayStatsObjects = new JSONArray();
		FilterByName filterByName = new FilterByName(arrayCities);
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();
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
		case "All":
		case "all":
			ArrayList<Stats> arrayStats2 = new ArrayList<>();
			while(!filterByName.getArrayCities().isEmpty() && filterByName.getArrayCities() != null) {
				arrayCitiesFiltered = filterByName.filter();
				StatsClouds statsClouds = new StatsClouds(arrayCitiesFiltered);
				Stats stats = statsClouds.calculate();
				stats.setName(arrayCitiesFiltered.get(0).getName());
				arrayStats.add(stats);
				StatsSpeed statsSpeed = new StatsSpeed(arrayCitiesFiltered);
				Stats stats2 = statsSpeed.calculate();
				stats2.setName(arrayCitiesFiltered.get(0).getName());
				arrayStats2.add(stats2);
			}
			StatsObject statsObject3 = new StatsObject(arrayStats, "nuvolosità", periodOfDatas);
			jsonArrayStatsObjects.add(statsObject3.getJsonObject());
			StatsObject statsObject4 = new StatsObject(arrayStats2, "velocità del vento", periodOfDatas);
			jsonArrayStatsObjects.add(statsObject4.getJsonObject());
			break;
		default: throw new InvalidTypeException();
		}
		return jsonArrayStatsObjects;
	}
	
	
}
