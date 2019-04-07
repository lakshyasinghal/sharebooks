package com.sharebooks.exception;

public final class EmptyPropertyException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String message = "Empty property in properties file";

	public EmptyPropertyException(){
		super(message);
	}

	public EmptyPropertyException(String propertyName){
		super(message + "\nProperty : " + propertyName);
	}

}
