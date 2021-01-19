package com.esame.exceptions;

/**
 * Classe InvalidBoxException
 * @author Riccardo Malpiedi
 *
 */
public class InvalidBoxException extends Exception {
	
	private static final long serialVersionUID = 2L;

	/**
	 * Costruttore della classe InvalidBoxException
	 */
	public InvalidBoxException() {
		super("Errore: Box di coordinate non valido");	
	}
}
