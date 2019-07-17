package com.sharebooks.sms.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUtility {

	public static void main(String[] args) {
		sendSms();
	}

	public static String sendSms() {
		try {
			// Construct data
			String apiKey = "apikey=" + "26ApMMZ/dLg-Fi6UpTIQT7XjlkeJbF0frQ4xfnMijG";
			String message = "&message=" + "Cock sucker.";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "8448054935";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
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
