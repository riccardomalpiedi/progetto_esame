package com.esame.filter;

import java.util.ArrayList;

import com.esame.model.City;

/**
 * Classe che filtra i dati secondo i nomi scelti dall'utente
 * @author Riccardo Malpiedi
 *
 */
public class FilterByChosenNames extends FilterParent implements Filter {
	/**
	 * ArrayList di nomi
	 */
	private ArrayList<String> names;

	/**
	 * Costruttore della classe FilterByChosenName
	 * @param arrayCities Citta' da filtrare
	 * @param names nomi di città
	 */
	public FilterByChosenNames(ArrayList<City> arrayCities, ArrayList<String> names) {
		super(arrayCities);
		this.names = names;
	}
	
	/**
	 * Implementazione del metodo filter. Dato un ArrayList di nomi e un ArrayList di City da filtrare,
	 * restituisce tutti gli oggetti City il cui nome e' incluso nell'ArrayList di nomi.
	 * @return arrayCitiesFiltered
	 */
	public ArrayList<City> filter() {
		ArrayList<City> arrayCitiesFiltered = new ArrayList<>();
		for(String name : names) {
			for(int i=0; i<arrayCities.size(); i++) {
				if(name.equals(arrayCities.get(i).getName())) {
					arrayCitiesFiltered.add(arrayCities.get(i));
				}
			}
		}
		return arrayCitiesFiltered;	
	}
	
	/**
	 * Getter di names
	 * @return the names
	 */
	public ArrayList<String> getNames() {
		return names;
	}

	/**
	 * Setter di names
	 * @param names the names to set
	 */
	public void setNames(ArrayList<String> names) {
		this.names = names;
	}
}
