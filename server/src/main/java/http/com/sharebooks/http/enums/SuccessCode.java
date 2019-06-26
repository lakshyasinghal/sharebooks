package com.sharebooks.http.enums;

import java.util.HashSet;
import java.util.Set;

public enum SuccessCode {
	TWO_HUNDRED(200), TWO_HUNDRED_ONE(201);

	private int code;

	private SuccessCode(int code) {
		this.code = code;
	}

	private static Set<Integer> successCodes = new HashSet<Integer>();

	static {
		successCodes.add(200);
		successCodes.add(201);
	}

	public static Set<Integer> successCodes() {
		return successCodes;
	}

	public int code() {
		return code;
	}
}
