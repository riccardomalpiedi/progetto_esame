package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

/**
 * Interfaccia per i filtri
 * @author Riccardo Vico
 *
 */
public interface Filter {
	
	/**
	 * Metodo per filtrare dati
	 * @return arrayList di citta' filtrate
	 */
	public ArrayList<City> filter();

}
