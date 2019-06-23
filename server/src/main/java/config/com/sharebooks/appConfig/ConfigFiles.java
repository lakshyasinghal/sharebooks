package com.sharebooks.appConfig;

public enum ConfigFiles {
	CACHE(1, "Cache.properties"), DATABASE(2, "Database.properties"), SERVER(3, "Server.properties"),
	PAYMENT(4, "Payment.properties"), DAO(5, "Dao.properties"), MISCELLANEOUS(6, "Miscellaneous.properties");

	private int id;
	private String fileName;

	private ConfigFiles(int id, String fileName) {
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
