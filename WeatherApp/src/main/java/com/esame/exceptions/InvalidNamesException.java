package com.esame.exceptions;

/**
 * Classe InvalidNamesException
 * @author Riccardo Malpiedi
 *
 */
public class InvalidNamesException extends Exception {

	private static final long serialVersionUID = 3L;

	/**
	 * Costruttore della classe InvalidNamesException
	 */
	public InvalidNamesException() {
		super("Errore: Elenco di nomi non valido");	
	}
}
