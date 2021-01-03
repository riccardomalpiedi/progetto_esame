package com.esame.stats;

import com.esame.model.Stats;

/**
 * @author Riccardo Malpiedi
 * 
 * interfaccia per classi che calcolano le statistiche
 */

public interface StatsInterface {
	/**
	 * metodo per calcolare le statistiche
	 * @return oggetto contenente statistiche calcolate
	 */
	public Stats calculate();
}
