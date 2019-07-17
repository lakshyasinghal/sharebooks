package com.sharebooks.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpUtility {

	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}

	public static double doubleVal(HttpServletRequest req, String name) {
		String val = req.getParameter(name);
		return Double.parseDouble(val);
	}

	public static double intVal(HttpServletRequest req, String name) {
		String val = req.getParameter(name);
		return Integer.parseInt(val);
	}

}
