package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

public class FilterByName extends FilterParent implements Filter{
	
	public FilterByName(ArrayList<City> arrayCities) {
		super(arrayCities);
	}
	
	public ArrayList<City> filter() {
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();
		arrayCitiesFiltered.add(arrayCities.get(0));
		arrayCities.remove(0);
		for(int i=0; i<arrayCities.size(); i++) {
			if(arrayCities.get(i).getName().equals(arrayCitiesFiltered.get(0).getName())) {
				arrayCitiesFiltered.add(arrayCities.get(i));
				arrayCities.remove(i);
				i--;
			}
		}
		return arrayCitiesFiltered;	
	}

}
