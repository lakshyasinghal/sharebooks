package com.sharebooks.exception;

public final class UnsafeAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String message = "Some outer class is trying to access the instance directly from the class";

	public UnsafeAccessException(){
		super(message);
	}


	public void log(){
		
	}
}
