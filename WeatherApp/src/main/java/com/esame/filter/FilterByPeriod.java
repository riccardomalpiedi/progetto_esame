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
	 * @param arrayCities Citta' da filtrare
	 * @param period periodo
	 */
	public FilterByPeriod(ArrayList<City> arrayCities, int period) {
		super(arrayCities);
		this.period = period;
	}
	
	/**
	 * Secondo costruttore della classe period
	 * @param arrayCities citta' da filtrare
	 */
	public FilterByPeriod(ArrayList<City> arrayCities) {
		super(arrayCities);
	}
	
	/**
	 * Implementazione del metodo filter. Prende tutti gli oggetti City la cui data e' compresa tra la data
	 * del primo oggetto (quindi la data piu' vecchia) e la stessa data a cui viene sommato il periodo.
	 * Questo gruppo di oggetti viene rimosso da arrayCities e poi restituito dal metodo.
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
