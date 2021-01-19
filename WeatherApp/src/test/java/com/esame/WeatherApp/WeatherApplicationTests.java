package com.esame.WeatherApp;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.esame.exceptions.InvalidBoxException;
import com.esame.model.City;
import com.esame.model.Stats;
import com.esame.stats.StatsClouds;

/**
 * Classe per i test
 * @author Riccardo Malpiedi
 * @author Riccardo Vico
 */
@SpringBootTest
public class WeatherApplicationTests {
	/**
	 * oggetto per calcolo statistiche
	 */
	private StatsClouds statsClouds;
	
	/**
	 * oggetto Stats
	 */
	private Stats stats;
	
	/**
	 * stringa contenente l'URL
	 */
	private String urlString;
	
	/**
	 * InvalidBoxException che abbiamo creato noi
	 */
	private InvalidBoxException invalidBoxException;
	
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
		stats = new Stats();
		
		String API_KEY = null;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("APIKey.txt"))) {
			API_KEY = bufferedReader.readLine();
		} catch (IOException e){
			e.printStackTrace();
		}
		String COORDINATES = "box non valido";
		urlString = "http://api.openweathermap.org/data/2.5/box/city?bbox=" + COORDINATES
				+ ",10&appid=" + API_KEY;
		
		invalidBoxException = new InvalidBoxException();
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
		stats = statsClouds.calculate();
		stats.setName("Milano");
		assertEquals("Milano", stats.getName());
		assertEquals(7.5, stats.getAverage());
		assertEquals(6.25, stats.getVariance());
	}
	
	/**
	 * Test che verifica che sia lanciata l'IOException quando usiamo un URL con un box non valido
	 */
	@SuppressWarnings("unused")
	@Test
	@DisplayName("Test 2: verifica che sia lanciata l'exception")
	void testException() {
		IOException exception = assertThrows(IOException.class, () -> {
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		});
	}
	
	/**
	 * Test che verifica che il messaggio dell'Exception sia corretto
	 */
	@Test
	@DisplayName("Test 3: verifica messaggio exceptions")
	void testException2() {
		assertEquals("Errore: Box di coordinate non valido", invalidBoxException.getMessage());
	}
	
	

}
