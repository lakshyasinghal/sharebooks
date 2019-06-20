package com.sharebooks.database.sql;

public enum Database {

	SHAREBOOKS_CORE(1, "Sharebooks_Core"), SHAREBOOKS_MASTER(2, "Sharebooks_Master"),
	SHAREBOOKS_USER_ACCOUNTS(3, "Sharebooks_UserAccounts"), SHAREBOOKS_USER_EXPERIENCE(4, "Sharebooks_UserExperience");

	private int id;
	private String desc;

	private Database(int id, String desc) {
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
