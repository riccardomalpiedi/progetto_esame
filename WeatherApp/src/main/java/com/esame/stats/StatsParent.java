package com.esame.stats;

import java.util.ArrayList;

import com.esame.model.City;

public class StatsParent {
	
	ArrayList<City> arrayCities = new ArrayList<>();
	
	/**
	 * costruttore
	 * @param arrayCities array su cui calcolare le statistiche
	 */
	public StatsParent(ArrayList<City> arrayCities) {
		this.arrayCities = arrayCities;
	}
}
