package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

public class FilterByName extends FilterParent implements Filter{
	
	public ArrayList<String> names;
	
	public FilterByName(ArrayList<City> arrayCities, ArrayList <String> names) {
		super(arrayCities);
		this.names = names;
	}
	
	public ArrayList <City> filter() {
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();
		for(int i=0; i<arrayCities.size(); i++) {
			for(String name : names) {
				if(arrayCities.get(i).getName().equals(name)) {
					arrayCitiesFiltered.add(arrayCities.get(i));
					arrayCities.remove(i);
					i--;
					break;
				}
			}
			
		}
		return arrayCitiesFiltered;	
	}

}
