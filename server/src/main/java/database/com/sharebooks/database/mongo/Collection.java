package com.sharebooks.database.mongo;

public enum Collection {
	USER(1, "User", "User_Accounts");

	private int id;
	private String name;
	private String dbName;

	private Collection(int id, String name, String dbName) {
		this.id = id;
		this.dbName = name;
		this.dbName = dbName;
	}

	public int id() {
		return id;
	}

	public String naam() {
		return name;
	}

	public String dbName() {
		return dbName;
	}

}
