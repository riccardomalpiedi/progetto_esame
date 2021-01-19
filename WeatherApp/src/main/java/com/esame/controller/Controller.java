package com.esame.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esame.exceptions.InvalidBoxException;
import com.esame.exceptions.InvalidNamesException;
import com.esame.exceptions.InvalidPeriodException;
import com.esame.exceptions.InvalidTypeException;
import com.esame.service.OpenWeatherService;

/**
 * Classe Controller
 * @author Riccardo Vico
 * @author Riccardo Malpiedi
 */
@RestController
public class Controller {
	@Autowired OpenWeatherService openWeatherService;
	
	/**
	 * Rotta get per visualizzare dati attuali su vento e nuvolosita'
	 * @param box di coordinate
	 * @return dati attuali su vento e nuvolosita'
	 * @throws InvalidBoxException 
	 */
	@GetMapping("/Data")
	public JSONArray getActualData(@RequestParam String box) throws InvalidBoxException {
		return openWeatherService.actualDataService(box);
	}
	
	/**
	 * Rotta get per visualizzare le statistiche del tipo richiesto filtrate secondo la periodicita' richiesta
	 * @param object JSONObject inserito dall'utente
	 * @return statistiche periodiche
	 * @throws InvalidTypeException 
	 * @throws InvalidNamesException 
	 * @throws InvalidPeriodException 
	 */
	@PostMapping("/PeriodicalStats")
	public JSONArray getPeriodicalStats(@RequestBody JSONObject object) throws InvalidTypeException, InvalidPeriodException, InvalidNamesException {
		return openWeatherService.periodicalStatsService(object);
	}
	
	/**
	 * Rotta get per visualizzare le statistiche settimanali del tipo richiesto
	 * @param object JSONObject inserito dall'utente
	 * @return statistiche settimanali
	 * @throws InvalidTypeException 
	 * @throws InvalidNamesException 
	 */
	@PostMapping("/WeeklyStats")
	public JSONArray getWeeklyStats(@RequestBody JSONObject object) throws InvalidTypeException, InvalidNamesException {
		return openWeatherService.weeklyStatsService(object);
	}
	
	/**
	 * Rotta get per visualizzare le statistiche giornaliere del tipo richiesto
	 * @param object JSONObject inserito dall'utente
	 * @return statistiche giornaliere
	 * @throws InvalidTypeException 
	 * @throws InvalidNamesException 
	 */
	@PostMapping("/DailyStats")
	public JSONArray getDailyStats(@RequestBody JSONObject object) throws InvalidTypeException, InvalidNamesException {
		return openWeatherService.dailyStatsService(object);
	}
	
	/**
	 * Rotta post per cambiare il box di coordinate 
	 * @param box
	 * @return risposta sulla corretta esecuzione
	 * @throws InvalidBoxException 
	 */
	@GetMapping("/ChangeBox")
	public String changeBox(@RequestParam String box) throws InvalidBoxException {
		return openWeatherService.changeBoxService(box);
	}
}


