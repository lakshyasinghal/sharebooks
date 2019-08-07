package com.sharebooks.dao.enums;

public enum RedisKey {
	CITIES("cities"), STATES("states"), BOOK_CATEGORIES("bookCategories");

	private String desc;

	private RedisKey(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
