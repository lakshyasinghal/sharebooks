package com.sharebooks.exception;

public class JedisConnectionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String message = "Jedis Connection exception";

	public JedisConnectionException() {
		super(message);
	}
}
