package com.sharebooks.exception;

public final class HttpRequestFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String message = "Http request failed";
	private int statusCode;
	private String resMessage;

	public HttpRequestFailedException(int statusCode, String resMessage) {
		super(HttpRequestFailedException.message + "\n" + "Status code => " + statusCode + "Response message => "
				+ resMessage);
		this.statusCode = statusCode;
		this.resMessage = resMessage;
	}

	public int statusCode() {
		return statusCode;
	}

	public String resMessage() {
		return resMessage;
	}
}
