package com.sharebooks.urlShortener.service;

import com.sharebooks.http.HttpClient;
import com.sharebooks.http.service.HttpService;
import com.sharebooks.urlShortener.entities.ShortUrlRequest;
import com.sharebooks.urlShortener.factory.ShortUrlRequestFactory;

public class UrlShortenerService {
	private static UrlShortenerService instance = null;
	private HttpService httpService = HttpService.instance();

	private UrlShortenerService() {
	}

	public static UrlShortenerService instance() {
		if (instance == null) {
			synchronized (UrlShortenerService.class) {
				if (instance == null) {
					instance = new UrlShortenerService();
				}
			}
		}
		return instance;
	}

	public String getShortUrl(String longUrl) throws Exception {
		ShortUrlRequest shortUrlRequest = ShortUrlRequestFactory.instance().create(longUrl);
		// get serialized http client
		HttpClient client = shortUrlRequest.serializeAsHttp();
		// make request and get response
		String data = httpService.makeRequest(client);
		return data;
	}
}
