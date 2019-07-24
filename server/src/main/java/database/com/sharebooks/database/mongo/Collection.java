package com.sharebooks.database.mongo;

public enum Collection {
	USERS(1, "User", "User_Accounts"), BOOKS(2, "Books", "Core"), SUBSCRIPTIONS(3, "Subscriptions", "User_Accounts");

	private int id;
	private String desc;
	private String dbName;

	private Collection(int id, String desc, String dbName) {
		this.id = id;
		this.desc = desc;
		this.dbName = dbName;
	}

	public int id() {
		return id;
	}

	public String desc() {
		return desc;
	}

	public String dbName() {
		return dbName;
	}

}
