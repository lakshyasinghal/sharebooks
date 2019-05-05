package com.sharebooks.coreEntities.enums;

public enum QuoteStatus {
	INITIAL(1, "Initial"), FINAL(2, "final");

	private int id;
	private String description;

	private QuoteStatus(int id , String description){
		this.id = id;
		this.description = description;
	}

	public static QuoteStatus valueOf(int id) {
		switch (id) {
		case 1:
			return INITIAL;
		case 2:
			return FINAL;
		default:
			return null;
		}
	}

	public int id() {
		return id;
	}

	public String description() {
		return description;
	}
}
