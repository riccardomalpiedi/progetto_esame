package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

public class FilterByName extends FilterParent implements Filter{
	
	public ArrayList <String> names;
	public FilterByName(ArrayList <City> arrayCities, ArrayList <String> names) {
		super(arrayCities);
		this.names = names;
	}
	
	public ArrayList <City> filter(){ //da ridefinire
		return arrayCities;		
	}

}
