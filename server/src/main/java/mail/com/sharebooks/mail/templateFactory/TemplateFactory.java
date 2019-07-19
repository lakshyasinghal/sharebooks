package com.sharebooks.mail.templateFactory;

import com.sharebooks.mail.enums.MailType;
import com.sharebooks.mail.templates.MailTemplate;
import com.sharebooks.mail.templates.PasswordResetLinkTemplate;

public class TemplateFactory {
	private static TemplateFactory instance;

	private TemplateFactory() {
	}

	public static TemplateFactory instance() {
		if (instance == null) {
			synchronized (TemplateFactory.class) {
				if (instance == null) {
					instance = new TemplateFactory();
				}
			}
		}

		return instance;
	}

	public MailTemplate create(MailType mailType, Object obj) {
		MailTemplate template = null;
		if (mailType == MailType.PASSWORD_RESET) {
			template = new PasswordResetLinkTemplate((String) obj);
		}
		return template;
	}
}
