package com.sharebooks.http;

import java.util.HashMap;
import java.util.Map;

import com.sharebooks.http.enums.Header;
import com.sharebooks.http.enums.HttpMethod;

public class Tester {

	public static void main(String[] args) {
		Tester tester = new Tester();
		// tester.testHttpClient();
		tester.testPaymentApi();
	}

	public void testHttpClient() {
		HttpClient client = new HttpClient.HttpClientBuilder().url("https://www.google.com").method(HttpMethod.GET)
				.build();
		try {
			client.makeRequest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testPaymentApi() {
		try {
			String url = "https://www.instamojo.com/api/1.1/payment-requests/";

			// headers
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put(Header.X_API_KEY.desc(), "eead929e9dd7f15f4abfedca6efd2ea4");
			headerMap.put(Header.X_AUTH_TOKEN.desc(), "d075bd6193bdb2f98b9c85f7ea2cc12d");

			// params
			Map<String, String> params = new HashMap<String, String>();
			params.put("amount", "500");
			params.put("purpose", "test payment api");

			HttpClient client = new HttpClient.HttpClientBuilder().url(url).method(HttpMethod.POST).parameters(params)
					.headerMap(headerMap).build();
			client.makeRequest();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
