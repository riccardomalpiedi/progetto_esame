package com.esame.filter;

import java.util.ArrayList;
import java.util.Collections;

import com.esame.model.City;

public class FilterParent {
	
	protected ArrayList<City> arrayCities;
	
	public FilterParent(ArrayList<City> arrayCities) {
		this.arrayCities = arrayCities;
		Collections.sort(this.arrayCities);
	}
	
	/**
	 * @return the arrayCities
	 */
	public ArrayList<City> getArrayCities() {
		return arrayCities;
	}

	/**
	 * @param arrayCities the arrayCities to set
	 */
	public void setArrayCities(ArrayList<City> arrayCities) {
		this.arrayCities = arrayCities;
	}

}
