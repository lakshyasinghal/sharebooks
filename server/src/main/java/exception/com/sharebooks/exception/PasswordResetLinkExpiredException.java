package com.sharebooks.exception;

public class PasswordResetLinkExpiredException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String message = "Password link has expired";

	public PasswordResetLinkExpiredException() {
		this("");
	}

	public PasswordResetLinkExpiredException(String message) {
		super(PasswordResetLinkExpiredException.message + "\n" + message);
	}
}
