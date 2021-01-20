package com.esame.stats;

import java.util.ArrayList;

import com.esame.model.City;

/**
 * Superclasse per le classi che calcolano le statistiche
 * @author Riccardo Malpiedi
 *
 */
public class StatsParent {
	
	/**
	 * ArrayList di citta' delle quali calcolare le statistiche
	 */
	ArrayList<City> arrayCities = new ArrayList<>();
	
	/**
	 * Costruttore di StatsParent
	 * @param arrayCities citta' di cui calcolare le statistiche
	 */
	public StatsParent(ArrayList<City> arrayCities) {
		this.arrayCities = arrayCities;
	}
}
