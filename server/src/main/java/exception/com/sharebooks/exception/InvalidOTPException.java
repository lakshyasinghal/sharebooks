
package com.sharebooks.exception;

public class InvalidOTPException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String message = "Incorrect OTP";

	public InvalidOTPException() {

	}

	public InvalidOTPException(String message) {
		super(InvalidOTPException.message + "\n" + message);
	}
}
