package com.djorquab.relational.relational.exceptions;

public class EmailAlreadyRegisteredException extends Exception {
	private static final long serialVersionUID = 7597129332141692611L;

	public EmailAlreadyRegisteredException(String msg) {
		super(msg);
	}
}
