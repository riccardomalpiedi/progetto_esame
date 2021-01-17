package com.esame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Classe contenente il main
 * @author Riccardo Vico
 * @author Riccardo Malpiedi
 *
 */

@SpringBootApplication
@EnableScheduling
public class WeatherApplication {
	
	/**
	 * Metodo main
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

}
