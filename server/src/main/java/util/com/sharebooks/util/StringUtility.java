package com.sharebooks.util;

public class StringUtility {

	public static boolean isEmptyOrNull(String s) {
		if (s == null) {
			return true;
		}
		s = s.trim();
		return s.equals("");
	}

	public static String removeCommaFromEnd(String inputStr) {
		if (inputStr == null) {
			return null;
		}
		char[] charArr = inputStr.toCharArray();

		int l = charArr.length;
		if (l > 0 && charArr[l - 1] == ',') {
			charArr[l - 1] = ' ';
		}
		return new String(charArr).trim();
	}

//	public static void main(String[] args) {
//		String testStr = "74 B, Sec 11,Faridabad,";
//		System.out.println(testStr);
//		System.out.println(removeCommaFromEnd(testStr));
//	}

}
