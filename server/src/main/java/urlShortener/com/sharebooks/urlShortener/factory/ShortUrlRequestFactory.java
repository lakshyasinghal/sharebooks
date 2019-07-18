package com.sharebooks.urlShortener.factory;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.UrlShortenerProperties;
import com.sharebooks.urlShortener.entities.ShortUrlRequest;
import com.sharebooks.urlShortener.entities.ShortUrlRequest.ShortUrlRequestBuilder;

public class ShortUrlRequestFactory {
	private static ShortUrlRequestFactory instance;

	private ShortUrlRequestFactory() {
	}

	public static ShortUrlRequestFactory instance() {
		if (instance == null) {
			synchronized (ShortUrlRequestFactory.class) {
				if (instance == null) {
					instance = new ShortUrlRequestFactory();
				}
			}
		}

		return instance;
	}

	public ShortUrlRequest create(String longUrl) {
		ShortUrlRequestBuilder b = new ShortUrlRequest.ShortUrlRequestBuilder();
		String api = AppConfig.urlShortenerProp(UrlShortenerProperties.API);
		String format = AppConfig.urlShortenerProp(UrlShortenerProperties.FORMAT);
		String access_token = AppConfig.urlShortenerProp(UrlShortenerProperties.ACCESS_TOKEN);
		b.api(api).format(format).accessToken(access_token).longUrl(longUrl);
		return b.build();
	}
}
