package com.esame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.simple.JSONObject;

/**
 * Classe StatsObject
 * @author Riccardo Vico
 * @author Riccardo Malpiedi
 */
public class StatsObject {
	
	/**
	 * ArrayList delle statistiche
	 */
	private ArrayList<Stats> arrayStats;
	
	/**
	 * Citta' con la media piu' alta
	 */
	private String maxCity;
	
	/**
	 * Citta' con la media piu' bassa
	 */
	private String minCity;
	
	/**
	 * Citta' con la varianza piu' alta
	 */
	private String maxVarianceCity;
	
	/**
	 * Tipo di statistica
	 */
	private String statsType;
	
	/**
	 * Periodo
	 */
	private String period;
	
	/**
	 * Costruttore della classe StatsObject
	 * @param arrayStats statistiche
	 * @param statsType tipo delle statistiche
	 * @param period periodo
	 */
	public StatsObject(ArrayList<Stats> arrayStats, String statsType, String period) {
		this.arrayStats = arrayStats;
		this.statsType = statsType;
		this.period = period;
		Comparator<Stats> comparatorAverage = new Comparator<Stats>() {
			public int compare(Stats s1, Stats s2) {
	            if(s1.getAverage() > s2.getAverage()) return 1;
	            if(s1.getAverage() < s2.getAverage()) return -1;
	            else return 0;
	        }
	    };
	    maxCity = Collections.max(arrayStats, comparatorAverage).getName();
	    minCity = Collections.min(arrayStats, comparatorAverage).getName();
	    Comparator<Stats> comparatorVariance = new Comparator<Stats>() {
			public int compare(Stats s1, Stats s2) {
				 if(s1.getVariance() > s2.getVariance()) return 1;
		         if(s1.getVariance() < s2.getVariance()) return -1;
		         else return 0;
	        }
	    };
	    maxVarianceCity = Collections.max(arrayStats, comparatorVariance).getName();
	}
	
	/**
	 * Metodo che restituisce il JSONObject
	 * @return JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("Statistiche", arrayStats);
		obj.put("Città con media più alta", maxCity);
		obj.put("Città con media più bassa", minCity);
		obj.put("Città con varianza massima", maxVarianceCity);
		obj.put("tipo", statsType);
		obj.put("periodo", period);
		return obj;
	}
	
	/**
	 * Getter di arrayStats
	 * @return arrayStats
	 */
	public ArrayList<Stats> getArrayStats() {
		return arrayStats;
	}
	/**
	 * Setter di arrayStats
	 * @param arrayStats statistiche
	 */
	public void setArrayStats(ArrayList<Stats> arrayStats) {
		this.arrayStats = arrayStats;
	}
	/**
	 * Getter di maxCity
	 * @return maxCity
	 */
	public String getMaxCity() {
		return maxCity;
	}
	/**
	 * Setter di maxCity
	 * @param maxCity citta' con media piu' alta
	 */
	public void setMaxCity(String maxCity) {
		this.maxCity = maxCity;
	}
	/**
	 * Getter di minCity
	 * @return minCity
	 */
	public String getMinCity() {
		return minCity;
	}
	/**
	 * Setter di minCity
	 * @param minCity citta' con media piu' bassa
	 */
	public void setMinCity(String minCity) {
		this.minCity = minCity;
	}
	/**
	 * Getter della citta' con varianza massima
	 * @return maxVarianceCity
	 */
	public String getMaxVarianceCity() {
		return maxVarianceCity;
	}
	/**
	 * Setter della citta' con la varianza massima
	 * @param maxVarianceCity citta' con varianza massima
	 */
	public void setMaxVarianceCity(String maxVarianceCity) {
		this.maxVarianceCity = maxVarianceCity;
	}
	/**
	 * Getter del tipo di stats
	 * @return statsType
	 */
	public String getStatsType() {
		return statsType;
	}
	/**
	 * Setter del tipo di stats
	 * @param statsType tipo delle statistiche
	 */
	public void setStatsType(String statsType) {
		this.statsType = statsType;
	}
	/**
	 * Getter del periodo
	 * @return period
	 */
	public String getPeriod() {
		return period;
	}
	/**
	 * Setter del periodo
	 * @param period periodo
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
}
