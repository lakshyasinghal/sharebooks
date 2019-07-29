package com.sharebooks.database.enums;

public enum DatabaseType {
	SQL(1), MONGO(2);

	private int id;

	private DatabaseType(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}
}
