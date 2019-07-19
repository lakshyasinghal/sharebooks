package com.sharebooks.mail.enums;

public enum MailClass {
	PASSWORD_RESET("com.sharebooks.mail.templates.PasswordResetLinkTemplate.PasswordResetMail");

	private String className;

	private MailClass(String className) {
		this.className = className;
	}

	public String className() {
		return className;
	}
}
