package com.esame.exceptions;

/**
 * Classe InvalidTypeException
 * @author Riccardo Vico
 *
 */
public class InvalidTypeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore della classe InvalidTypeException
	 */
	public InvalidTypeException() {
		super("Errore: Tipo non valido");	
	}

}
