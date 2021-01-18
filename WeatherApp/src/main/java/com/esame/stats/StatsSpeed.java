package com.esame.stats;

import java.util.ArrayList;

import com.esame.model.City;
import com.esame.model.Stats;

/**
 * Classe StatsSpeed estende StatsParent implementa StatsInterface
 * @author Riccardo Malpiedi
 */
public class StatsSpeed extends StatsParent implements StatsInterface {
	
	/**
	 * Costruttore della classe StatsSpeed
	 * @param arrayCities
	 */
	public StatsSpeed(ArrayList<City> arrayCities) {
		super(arrayCities);
	}

	/**
	 * Calcola le statistiche della velocita' del vento
	 * @return stats
	 */
	public Stats calculate() {
		Stats stats = new Stats();
		double sum=0;
		double n=0;
		for(int i=0; i<arrayCities.size(); i++) {
			sum += arrayCities.get(i).getSpeed();
		}
		double average = sum/arrayCities.size();
		for(int i=0; i<arrayCities.size(); i++) {
			n += Math.pow((arrayCities.get(i).getSpeed() - average), 2);
		}
		double variance = n/arrayCities.size();
		stats.setAverage(average);
		stats.setVariance(variance);
		return stats;
	}
}
