package com.esame.controller;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esame.service.OpenWeather;

@RestController
public class Controller {
	@Autowired OpenWeather openWeather;
	
	@GetMapping("/Data")
	public JSONArray getActualData(@RequestParam String box) {
		return openWeather.downloadArray(box);
	}
	
	@GetMapping("/Stats")
	public JSONArray getStats(@RequestParam String type, @RequestParam int period) {
		return openWeather.StatsService(type, period);
	}
}
