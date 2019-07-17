package com.sharebooks.http.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.sharebooks.exception.HttpRequestFailedException;
import com.sharebooks.http.HttpClient;
import com.sharebooks.http.HttpResponse;
import com.sharebooks.http.enums.SuccessCode;

public class HttpService {
	private static Logger LOGGER = Logger.getLogger(HttpService.class);
	private static HttpService instance;

	private HttpService() {

	}

	public static HttpService instance() {
		if (instance == null) {
			synchronized (HttpService.class) {
				if (instance == null) {
					instance = new HttpService();
				}
			}
		}
		return instance;
	}

	public String makeRequest(HttpClient client) throws Exception {
		HttpResponse httpResponse = client.makeRequest();
		int statusCode = httpResponse.status();
		String resMessage = httpResponse.message();
		String data = null;
		Set<Integer> httpSuccessCodes = SuccessCode.successCodes();
		if (httpSuccessCodes.contains(statusCode)) {
			data = httpResponse.data();
		} else {
			throw new HttpRequestFailedException(statusCode, resMessage);
		}

		LOGGER.debug("Response Data => " + data);
		return data;
	}

}
