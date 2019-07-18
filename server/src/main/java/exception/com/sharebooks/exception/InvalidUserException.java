package com.sharebooks.exception;

public class InvalidUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String message = "Invalid user";

	public InvalidUserException() {
		this("");
	}

	public InvalidUserException(String message) {
		super(InvalidUserException.message + "\n" + message);
	}
}
