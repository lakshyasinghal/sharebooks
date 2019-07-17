package com.sharebooks.exception;

public class IncorrectInstanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String message = "Incorrect instance found.";

	public IncorrectInstanceException(String message) {
		super(IncorrectInstanceException.message + "\n" + message);
	}
}
