package com.sharebooks.entities.coreEntities.enums;

public enum EntityType {
	BOOK(1, "book"), USER(2, "user"), BOOK_REQUEST(3, "bookRequest"), ORDER(4, "order"),
	BOOK_CATEGORY(5, "bookCategory"), STATE(6, "state"), CITY(7, "city"), NOTIFICATION(8, "notification"),
	PREFERENCE(9, "preference"), Quote(10, "quote"), ONE_TIME_PASSWORD(11, "otp"),
	PASSWORD_RESET_LINK(12, "passwordResetLink");

	private int id;
	private String desc; // description

	private EntityType(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static EntityType valueOf(int id) {
		switch (id) {
		case 1:
			return BOOK;
		case 2:
			return USER;
		case 3:
			return BOOK_REQUEST;
		case 4:
			return ORDER;
		default:
			return null;
		}
	}

	public int id() {
		return id;
	}

	public String desc() {
		return desc;
	}
}
