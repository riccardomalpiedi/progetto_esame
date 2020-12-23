package com.esame.stats;

import java.util.ArrayList;

import com.esame.model.City;
import com.esame.model.Stats;

public class StatsClouds extends StatsParent implements StatsInterface {
	
	public StatsClouds(ArrayList<City> arrayCities) {
		super(arrayCities);
	}
	
	public Stats calculate() {
		Stats stats = new Stats();
		double sum=0;
		double n=0;
		for(int i=0; i<arrayCities.size(); i++) {
			sum += arrayCities.get(i).getClouds();
		}
		double average = sum/arrayCities.size();
		for(int i=0; i<arrayCities.size(); i++) {
			n += Math.pow((arrayCities.get(i).getClouds() - average), 2);
		}
		double variance = n/arrayCities.size();
		stats.setAverage(average);
		stats.setVariance(variance);
		return stats;
	}
}
