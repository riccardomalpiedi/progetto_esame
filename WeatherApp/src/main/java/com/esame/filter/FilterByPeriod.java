package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

/**
 * Classe per filtrare i dati per periodo
 * @author Riccardo Malpiedi
 *
 */
public class FilterByPeriod extends FilterParent implements Filter {
	
	/**
	 * periodo
	 */
	protected int period;
	
	/**
	 * Costruttore della classe period
	 * @param arrayCities
	 * @param period
	 */
	public FilterByPeriod(ArrayList<City> arrayCities, int period) {
		super(arrayCities);
		this.period = period;
	}
	
	/**
	 * Costruttore della classe period
	 * @param arrayCities
	 */
	public FilterByPeriod(ArrayList<City> arrayCities) {
		super(arrayCities);
	}
	
	/**
	 * Implementazione del metodo filter
	 * @return arrayCitiesFiltered
	 */
	public ArrayList<City> filter() {
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();
		arrayCitiesFiltered.add(arrayCities.get(0));
		arrayCities.remove(0);
		for(int i=0; i<arrayCities.size(); i++) {
			if( (arrayCities.get(i).getDate().isAfter(arrayCitiesFiltered.get(0).getDate())
			&& arrayCities.get(i).getDate().isBefore(arrayCitiesFiltered.get(0).getDate().plusDays(period)) )
			|| arrayCities.get(i).getDate().isEqual(arrayCitiesFiltered.get(0).getDate())) {
				arrayCitiesFiltered.add(arrayCities.get(i));
				arrayCities.remove(i);
				i--;
			}
			
		}
		return arrayCitiesFiltered;
	}
}
