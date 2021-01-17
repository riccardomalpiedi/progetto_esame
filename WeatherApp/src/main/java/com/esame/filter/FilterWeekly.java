package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

/**
 * Classe FIlterWeekly
 * @author Riccardo Malpiedi
 *
 */
public class FilterWeekly extends FilterByPeriod {
	
	/**
	 * Costruttore di FilterWeekly (imposta il periodo a 7)
	 * @param arrayCities
	 */
	public FilterWeekly(ArrayList<City> arrayCities) {
		super(arrayCities);
		this.period = 7;
	}
}
