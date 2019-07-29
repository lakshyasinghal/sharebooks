package com.sharebooks.database.enums;

public enum Database {
	CORE(1, "Sharebooks_Core"), MASTER(2, "Sharebooks_Master"), USER_ACCOUNTS(3, "Sharebooks_UserAccounts"),
	USER_EXPERIENCE(4, "Sharebooks_UserExperience"), PAYMENTS(5, "Sharebooks_Payments");

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
