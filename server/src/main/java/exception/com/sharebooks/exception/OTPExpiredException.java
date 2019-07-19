package com.sharebooks.exception;

public class OTPExpiredException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String message = "OTP is expired";

	public OTPExpiredException() {
		this("");
	}

	public OTPExpiredException(String message) {
		super(OTPExpiredException.message + "\n" + message);
	}
}
