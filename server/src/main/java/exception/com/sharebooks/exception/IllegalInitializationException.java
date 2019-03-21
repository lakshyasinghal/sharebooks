package com.sharebooks.exception;

public final class IllegalInitializationException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String message = "Some class is illegally initializing a resource";

	public IllegalInitializationException(){
		super(message);
	}


	public void log(){
		
	}
}
