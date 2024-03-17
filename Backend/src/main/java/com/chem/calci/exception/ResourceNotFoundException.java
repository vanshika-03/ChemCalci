package com.chem.calci.exception;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException() {
		super("Compound passed is not correct");
	}
	
}
