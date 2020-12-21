package com.esame.controller;

import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esame.service.OpenWeather;

@RestController
public class Controller {
	
	@GetMapping("/Data")
	public JSONArray getActualData(@RequestParam String box) {
		OpenWeather openWeather = new OpenWeather();
		return openWeather.downloadArray(box);
	}
}
