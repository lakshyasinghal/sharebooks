package com.sharebooks.sms.enums;

public enum TextLocalErrorCode {
	NRS(4, "No recipients specified"), NMC(5, "No message content"), MTL(6, "Message too long"),
	IC(7, "Insufficient credits"), ISD(8, "Invalid schedule date"), SDIP(9, "Schedule date is in the past"),
	IGI(10, "Invalid group ID"), SGE(11, "Selected group is empty"), INF(32, "Invalid number format"),
	TMN(33, "You have supplied too many numbers"), SBGIAN(34, "You have supplied both a group ID and a set of numbers"),
	ISN(43, "Invalid sender name"), NSNS(44, "No sender name specified"), NVNS(51, "No valid numbers specified"),
	STOA(191, "Schedule time is outside that allowed"), CSMATT(192, "You cannot send message at this time");

	private int id;
	private String desc;

	private TextLocalErrorCode(int id, String desc) {
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
