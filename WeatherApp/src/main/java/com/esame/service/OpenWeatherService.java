package com.esame.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.esame.exceptions.InvalidTypeException;
import com.esame.filter.FilterByPeriod;
import com.esame.filter.FilterDaily;
import com.esame.filter.FilterWeekly;
import com.esame.model.City;

/**
 * Classe Service
 * @author Riccardo Vico
 * @author Riccardo Malpiedi
 */
@Service
public class OpenWeatherService {
	
	/**
	 * Metodo che esegue chiamata all'API e jsonparsing
	 * @param box box di coordinate
	 * @return info attuali su città interne al box
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
			    	LocalDateTime date = LocalDateTime.ofEpochSecond(dateInEpoch, 0,
			    			                                         OffsetDateTime.now().getOffset());
			    	JSONObject o2 = (JSONObject)o1.get("wind");
			    	double speed = Double.parseDouble(o2.get("speed").toString());
			    	int deg = Integer.parseInt(o2.get("deg").toString());
			    	JSONObject o3 = (JSONObject)o1.get("clouds");
			    	double clouds = Double.parseDouble(o3.get("today").toString());
			    	City city = new City(name, speed, deg, clouds, date);
			    	jsonArrayCities.add(city.getJsonObject());
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonArrayCities;
			
	}
	
	/**
	 * Metodo che legge dati da storico e calcola le statistiche del tipo richiesto
	 * @param type tipo (es. nuvolosità)
	 * @return statistiche richieste
	 * @throws InvalidTypeException 
	 */
	public JSONArray statsService(String type) throws InvalidTypeException {
		ArrayList<City> arrayCities = OpenWeatherUtils.readCSV();
		String periodOfDatas = "da " + arrayCities.get(0).getDate().toString() + " a " + 
                arrayCities.get(arrayCities.size()-1).getDate().toString();
		return OpenWeatherUtils.statsUtil(arrayCities, type, periodOfDatas);
		
	}
	
	/**
	 * Metodo che legge dati da storico, li filtra secondo la periodicita' indicata e
	 * calcola le statistiche del tipo richiesto
	 * @param type tipo (es. nuvolosita')
	 * @param period periodicta' espressa in giorni
	 * @return statistiche rischieste
	 * @throws InvalidTypeException 
	 */
	@SuppressWarnings("unchecked")
	public JSONArray periodicalStatsService(String type, int period) throws InvalidTypeException {
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
	
	/**
	 * Metodo che legge dati da storico e calcola statistiche settimanali del tipo richiesto
	 * @param type tipo (es. nuvolosita')
	 * @return statistiche richieste
	 * @throws InvalidTypeException 
	 */
	@SuppressWarnings("unchecked")
	public JSONArray weeklyStatsService(String type) throws InvalidTypeException {
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
	
	/**
	 * Metodo che legge dati da storico e calcola statistiche giornaliere del tipo richiesto
	 * @param type tipo (es. nuvolosita')
	 * @return statistiche richieste
	 * @throws InvalidTypeException 
	 */
	@SuppressWarnings("unchecked")
	public JSONArray dailyStatsService(String type) throws InvalidTypeException {
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
	
	/**
	 * Metodo per cambiare il box di coordinate delle citta' in osservazione
	 * @param box box box di coordinate
	 * @return stringa che indica se l'operazione è andata a buon fine
	 * @throws InvalidBoxException 
	 */
	public String changeBoxService(String box) {
		String result = OpenWeatherUtils.API_Call(box);
		try {
			JSONObject obj = (JSONObject) JSONValue.parseWithException(result);
			if(obj.get("cod").toString().equals("200")) {
				File file = new File("box.txt");
				try {
					if(!file.exists())
						file.createNewFile();
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				try {
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("box.txt"));
					bufferedWriter.write(box);
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "Operazione Eseguita con Successo";
			} 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "Box di coordinate non valido";
	}
	
}
