package com.sharebooks.mail.enums;

public enum MailBodyContentType {

	HTML(1, "text/html");

	private int id;
	private String desc;

	private MailBodyContentType(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int id() {
		return id;
	}

	public String desc() {
		return desc;
	}
}
