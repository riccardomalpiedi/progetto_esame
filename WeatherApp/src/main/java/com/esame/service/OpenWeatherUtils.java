package com.esame.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.esame.model.City;

public class OpenWeatherUtils {
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayCities;
	}
	
	
}
