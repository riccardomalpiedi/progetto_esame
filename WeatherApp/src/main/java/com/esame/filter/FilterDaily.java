package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

/**
 * Classe FilterDaily
 * @author Riccardo Malpiedi
 *
 */
public class FilterDaily extends FilterByPeriod {
	
	/**
	 * Costruttore di FilterDaily (imposta il periodo a 1)
	 * @param arrayCities
	 */
	public FilterDaily(ArrayList<City> arrayCities) {
		super(arrayCities);
		this.period = 1;
	}
	
}
