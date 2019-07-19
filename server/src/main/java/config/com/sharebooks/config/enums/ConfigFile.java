package com.sharebooks.config.enums;

public enum ConfigFile {
	CACHE(1, "Cache.properties"), DATABASE(2, "Database.properties"), SERVER(3, "Server.properties"),
	PAYMENT(4, "Payment.properties"), DAO(5, "Dao.properties"), MISCELLANEOUS(6, "Miscellaneous.properties"),
	MAIL(7, "Mail.properties"), GEOCODING(8, "Geocoding.properties"), SMS(9, "Sms.properties"),
	TASKS(10, "Tasks.properties");

	private int id;
	private String fileName;

	private ConfigFile(int id, String fileName) {
		this.id = id;
		this.fileName = fileName;
	}

	public int id() {
		return id;
	}

	public String fileName() {
		return fileName;
	}
}
