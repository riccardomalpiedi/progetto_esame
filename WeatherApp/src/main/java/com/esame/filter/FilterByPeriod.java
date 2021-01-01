package com.esame.filter;

import java.util.ArrayList;
import java.util.Collections;

import com.esame.model.City;

public class FilterByPeriod extends FilterParent implements Filter {
	
	protected int period;
	
	public FilterByPeriod(ArrayList<City> arrayCities, int period) {
		super(arrayCities);
		this.period = period;
		Collections.sort(this.arrayCities);
	}
	
	public FilterByPeriod(ArrayList<City> arrayCities) {
		super(arrayCities);
	}
	
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
