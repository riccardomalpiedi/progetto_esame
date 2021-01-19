package com.esame.exceptions;

/**
 * Classe InvalidPeriodException
 * @author Riccardo Malpiedi
 *
 */
public class InvalidPeriodException extends Exception {

	private static final long serialVersionUID = 4L;

	/**
	 * Costruttore della classe InvalidPeriodException
	 */
	public InvalidPeriodException() {
		super("Errore: Periodo non valido (deve essere un intero)");	
	}
}
