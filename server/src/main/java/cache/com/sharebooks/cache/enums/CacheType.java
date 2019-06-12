package com.sharebooks.cache.enums;

public enum CacheType {
	LRU(1, "LRU", "Least recently used"), MRU(2, "MRU", "Most recently used"), MFU(3, "MFU", "Most frequently used"),
	REDIS(4, "Redis", "Redis");

	private int id;
	private String alias;
	private String desc;

	private CacheType(int id, String alias, String desc) {
		this.id = id;
		this.alias = alias;
		this.desc = desc;
	}

	public static CacheType getCacheType(String alias) {
		switch (alias) {
		case "LRU":
			return LRU;
		case "MRU":
			return MRU;
		case "MFU":
			return MFU;
		case "Redis":
			return REDIS;
		default:
			return null;
		}
	}

	public static CacheType getCacheType(int id) {
		switch (id) {
		case 1:
			return LRU;
		case 2:
			return MRU;
		case 3:
			return MFU;
		case 4:
			return REDIS;
		default:
			return null;
		}
	}

	public int id() {
		return id;
	}

	public String alias() {
		return alias;
	}

	public String desc() {
		return desc;
	}
}
