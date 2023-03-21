package com.ventura013.services.exception;

public class RequiredObjectIsNullException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RequiredObjectIsNullException() {
		super("It is not allowed to persist a null object!");
	}
	
	public RequiredObjectIsNullException(String msg) {
		super(msg);
	}
}
