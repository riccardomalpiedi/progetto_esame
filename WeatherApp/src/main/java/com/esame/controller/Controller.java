package com.esame.controller;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esame.service.OpenWeatherService;

@RestController
public class Controller {
	@Autowired OpenWeatherService openWeatherService;
	
	@GetMapping("/Data")
	public JSONArray getActualData(@RequestParam String box) {
		return openWeatherService.actualDataService(box);
	}
	
	@GetMapping("/Stats")
	public JSONArray getStats(@RequestParam String type) {
		return openWeatherService.statsService(type);
	}
	
	@GetMapping("/PeriodicalStats")
	public JSONArray getStats(@RequestParam String type, @RequestParam int period) {
		return openWeatherService.periodicalStatsService(type, period);
	}
}
