package com.sharebooks.exception;

public class NonFunctionalMethodException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String message = "Non-functional method.Please check the code from where this method is being called.";
	
	public NonFunctionalMethodException(){
		this("");
	} 
	
	public NonFunctionalMethodException(String message){
		super(NonFunctionalMethodException.message + "\n" + message);
	}
}
