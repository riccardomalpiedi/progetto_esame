package com.esame.filter;

import java.util.ArrayList;
import java.util.Collections;

import com.esame.model.City;

/**
 * Superclasse per il filtraggio dei dati 
 * @author Riccardo Vico
 * @author Riccardo Malpiedi
 */
public class FilterParent {
	
	/**
	 * ArrayList di citta' da filtrare
	 */
	protected ArrayList<City> arrayCities;
	
	/**
	 * Costruttore di FilterParent (ordina gli elemnti dati)
	 * @param arrayCities
	 */
	public FilterParent(ArrayList<City> arrayCities) {
		this.arrayCities = arrayCities;
		Collections.sort(this.arrayCities);
	}
	
	/**
	 * Getter di arrayCities
	 * @return the arrayCities
	 */
	public ArrayList<City> getArrayCities() {
		return arrayCities;
	}

	/**
	 * Setter di ArrayCities
	 * @param arrayCities the arrayCities to set
	 */
	public void setArrayCities(ArrayList<City> arrayCities) {
		this.arrayCities = arrayCities;
	}

}
