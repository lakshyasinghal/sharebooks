package com.sharebooks.exception;

public class UsernameAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private static final String message = "The username already exists.";
	
	public UsernameAlreadyExistsException(){
		super(message);
	}
}
