package com.betacom.jpa.exception;

public class AcademyException extends Exception{

	private static final long serialVersionUID = 1L;

	public AcademyException() {
		super();
	}
	
	public AcademyException(String messaggio) {
		super(messaggio);
	}
	
}