package com.esame.WeatherApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.esame.model.City;
import com.esame.model.Stats;
import com.esame.stats.StatsClouds;

/**
 * Classe per i test
 * @author Riccardo Malpiedi
 * @author Riccardo Vico
 */
@SpringBootTest
class WeatherApplicationTests {
	/**
	 * oggetto per calcolo statistiche
	 */
	private StatsClouds statsClouds;
	
	/**
	 * primo oggetto stats
	 */
	private Stats stats1;
	/**
	 * secondo oggetto stats
	 */
	private Stats stats2;
	
	/**
	 * Inizializza gli oggetti da testare
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		LocalDateTime date = LocalDateTime.now();
		City city1 = new City("Milano", 10, 2, 5, date);
		City city2 = new City("Milano", 12, 2, 10, date);
		ArrayList<City> arrayCities = new ArrayList<>();
		arrayCities.add(city1);
		arrayCities.add(city2);
		statsClouds = new StatsClouds(arrayCities);
		stats1 = new Stats("Milano", 7.5, 6.25);
		stats2 = new Stats();
		
	}
	
	/**
	 * tearDown
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Test che verifica la correttezza del calcolo delle statistiche
	 */
	@Test
	@DisplayName("Test 1: verifica correttezza calcolo statistiche")
	void testStats() {
		stats2 = statsClouds.calculate();
		stats2.setName("Milano");
		assertEquals(stats1.getName(), stats2.getName());
		assertEquals(stats1.getAverage(), stats2.getAverage());
		assertEquals(stats1.getVariance(), stats2.getVariance());
	}

}
