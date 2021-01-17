package com.esame.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	 * @param box
	 * @return box con dati attuali
	 */
	@GetMapping("/Data")
	public JSONArray getActualData(@RequestParam String box) {
		return openWeatherService.actualDataService(box);
	}
	
	/**
	 * Rotta get per visualizzare le statistiche del tipo richiesto
	 * @param type
	 * @return statistiche
	 */
	@GetMapping("/Stats")
	public JSONArray getStats(@RequestParam String type) {
		return openWeatherService.statsService(type);
	}
	
	/**
	 * Rotta get per visualizzare le statistiche del tipo richiesto filtrate secondo la periodicita' richiesta
	 * @param type
	 * @param period
	 * @return statistiche periodiche
	 */
	@GetMapping("/PeriodicalStats")
	public JSONArray getPeriodicalStats(@RequestParam String type, @RequestParam int period) {
		return openWeatherService.periodicalStatsService(type, period);
	}
	
	/**
	 * Rotta get per visualizzare le statistiche settimanali del tipo richiesto
	 * @param type
	 * @return statistiche settimanali
	 */
	@GetMapping("/WeeklyStats")
	public JSONArray getWeeklyStats(@RequestParam String type) {
		return openWeatherService.weeklyStatsService(type);
	}
	
	/**
	 * Rotta get per visualizzare le statistiche giornaliere del tipo richiesto
	 * @param type
	 * @return statistiche giornaliere
	 */
	@GetMapping("/DailyStats")
	public JSONArray getDailyStats(@RequestParam String type) {
		return openWeatherService.dailyStatsService(type);
	}
	
	/**
	 * Rotta post per cambiare il box di coordinate 
	 * @param box
	 * @return risposta sulla corretta esecuzione
	 */
	@PostMapping("/ChangeBox")
	public String changeBox(@RequestBody JSONObject box) {
		return openWeatherService.changeBoxService(box.get("box").toString());
	}
}


