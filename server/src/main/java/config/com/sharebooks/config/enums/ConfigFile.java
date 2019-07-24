package com.sharebooks.config.enums;

public enum ConfigFile {
	CACHE(1, "Cache.properties"), TASKS(2, "Tasks.properties"), SERVER(3, "Server.properties"),
	PAYMENT(4, "Payment.properties"), DAO(5, "Dao.properties"), MISCELLANEOUS(6, "Miscellaneous.properties"),
	MAIL(7, "Mail.properties"), GEOCODING(8, "Geocoding.properties"), SMS(9, "Sms.properties"),
	URL_SHORTENER(10, "UrlShortener.properties"), DATABASE_CORE(11, "Database_Core.properties"),
	DATABASE_MASTER(12, "Database_Master.properties"), DATABASE_USERACCOUNTS(13, "Database_UserAccounts.properties"),
	DATABASE_USEREXPERIENCE(14, "Database_UserExperience.properties"),
	DATABASE_PAYMENTS(15, "Database_Payments.properties");

	private int id;
	private String fileName;
	// private Map<String, String> fileMap;

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

//	public Map<String, String> fileMap() {
//		return fileMap;
//	}

//	private void fileMap(Map<String, String> fileMap) {
//		this.fileMap = fileMap;
//	}
}
