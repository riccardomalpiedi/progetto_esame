package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

public class FilterDaily extends FilterByPeriod {
	
	public FilterDaily(ArrayList<City> arrayCities) {
		super(arrayCities);
		this.period = 1;
	}
	
}
