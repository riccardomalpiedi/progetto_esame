package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

public class FilterByPeriod extends FilterParent implements Filter {
	
	protected int period;
	
	public FilterByPeriod(ArrayList<City> arrayCities, int period) {
		super(arrayCities);
		this.period = period;
	}
	
	public FilterByPeriod(ArrayList<City> arrayCities) {
		super(arrayCities);
	}
	
	public ArrayList<City> filter() {
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();
		arrayCitiesFiltered.add(arrayCities.get(0));
		arrayCities.remove(0);
		int j=0;
		for(int i=0; i<arrayCities.size(); i++) {
			if(arrayCities.get(i).getDate().isAfter(arrayCitiesFiltered.get(j).getDate())
			&& arrayCities.get(i).getDate().isBefore(arrayCitiesFiltered.get(j).getDate().plusDays(period))) {
				arrayCitiesFiltered.add(arrayCities.get(i));
				arrayCities.remove(i);
				i--;
			}
			
		}
		return arrayCitiesFiltered;
	}
}
