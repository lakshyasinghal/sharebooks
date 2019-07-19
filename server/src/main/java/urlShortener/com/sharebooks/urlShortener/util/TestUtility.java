package com.sharebooks.urlShortener.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUtility {

	public static void main(String[] args) {
		String shortenedUrl = shortenUrl("https://www.sharebooks.in/user/3082308u3208u208u023u/password");
	}

	public static String shortenUrl(String longUrl) {
		try {
			// Construct data
			String access_token = "access_token=" + "b3786d6b17c8a497be7b6191d484c86a31d2539d";
			String longurl = "&longUrl=" + longUrl;
			String format = "&format=txt";
			String httpUrl = "https://api-ssl.bitly.com/v3/shorten?" + access_token + longurl + format;
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL(httpUrl).openConnection();
			// String data = access_token + url;
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			// conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println(stringBuffer.toString());
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}

}
