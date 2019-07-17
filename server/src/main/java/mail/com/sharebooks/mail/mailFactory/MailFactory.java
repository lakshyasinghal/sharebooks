package com.sharebooks.mail.mailFactory;

import com.sharebooks.mail.entities.Mail;
import com.sharebooks.mail.enums.MailType;
import com.sharebooks.mail.templates.MailTemplate;

public class MailFactory {
	private static MailFactory instance;
	private ErrorMailFactory errorMailFactory = ErrorMailFactory.instance();

	private MailFactory() {

	}

	public static MailFactory instance() {
		if (instance == null) {
			synchronized (MailFactory.class) {
				if (instance == null) {
					instance = new MailFactory();
				}
			}
		}

		return instance;
	}

	public Mail createMail(MailType mailType, MailTemplate template) {
		if (mailType == MailType.ERROR) {
			return (Mail) errorMailFactory.createMail(template);
		}

		return null;
	}
}
