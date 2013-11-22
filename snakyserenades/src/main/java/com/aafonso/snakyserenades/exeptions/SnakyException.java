/*
 * SnakyException
 * 
 * 20131121 Alfonso Afonso
 * 
 * Exception Class to manage our own exceptions and throws it when we need
 *  
 */
package com.aafonso.snakyserenades.exeptions;

public class SnakyException extends Exception
{

	private static final long serialVersionUID = 1L;

	private String message;
	
	public SnakyException(String info)
	{
		// Set error message
		this.message = info;
	}
	public String getMessage() {
		return message;
	}
}