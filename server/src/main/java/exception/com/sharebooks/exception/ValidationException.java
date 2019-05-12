package com.sharebooks.exception;

public class ValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private static final String message = "Exception occurred during validation.";

	public ValidationException(String message){
		super(ValidationException.message + "\n" + message);
	}
}
