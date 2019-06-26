package com.sharebooks.http;

import org.apache.log4j.Logger;

public class HttpResponse {
	private static final Logger LOGGER = Logger.getLogger(HttpResponse.class);

	private int status;
	private String message;
	private String data;

	public HttpResponse(int status, String message, String data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int status() {
		return status;
	}

	public String message() {
		return message;
	}

	public String data() {
		return data;
	}
}
