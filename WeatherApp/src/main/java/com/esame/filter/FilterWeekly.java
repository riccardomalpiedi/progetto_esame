package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

public class FilterWeekly extends FilterByPeriod {
	
	public FilterWeekly(ArrayList<City> arrayCities) {
		super(arrayCities);
		this.period = 7;
	}
}
