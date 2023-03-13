package com.ventura013.services.exception;

public class ExceptionServices extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ExceptionServices(String msg) {
		super(msg);
	}
}
