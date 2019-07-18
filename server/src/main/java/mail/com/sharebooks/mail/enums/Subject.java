package com.sharebooks.mail.enums;

public enum Subject {
	INTERNAL_SERVER_ERROR("Internal Server Error"), PASSWORD_RESET_LINK("Sharebooks Password Reset Link");

	private String desc;

	private Subject(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
