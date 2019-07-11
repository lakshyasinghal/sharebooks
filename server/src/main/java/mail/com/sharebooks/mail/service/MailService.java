package com.sharebooks.mail.service;

public class MailService {
	private static MailService instance = null;

	private MailService() {

	}

	public static MailService instance() {
		if (instance == null) {
			synchronized (MailService.class) {
				if (instance == null) {
					instance = new MailService();
				}
			}
		}
		return instance;
	}

	public void sendErrorMail(Exception ex) {

	}

}
