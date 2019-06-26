package com.sharebooks.util;

public class StringUtils {

	public static boolean isEmptyOrNull(String s) {
		if (s == null) {
			return true;
		}
		s = s.trim();
		return s.equals("");
	}
}
