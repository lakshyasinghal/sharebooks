package com.sharebooks.exception;

public class IllegalAccessException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String message = "Class instance being accessed illegally.";

	public IllegalAccessException() {
		this("");
	}

	public IllegalAccessException(String className) {
		super(IllegalAccessException.message + "\n" + className);
	}
}
