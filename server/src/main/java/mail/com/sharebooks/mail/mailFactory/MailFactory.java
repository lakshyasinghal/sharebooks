package com.sharebooks.mail.mailFactory;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.MailProperties;
import com.sharebooks.mail.entities.Mail;
import com.sharebooks.mail.entities.PasswordResetMail;
import com.sharebooks.mail.enums.MailType;
import com.sharebooks.mail.templates.MailTemplate;
import com.sharebooks.mail.templates.PasswordResetLinkTemplate;

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

	public Mail createMail(MailType mailType, MailTemplate template, String[] emailIds) {
		Mail mail = null;
		if (mailType == MailType.ERROR) {
			mail = errorMailFactory.createMail(template);
		} else if (mailType == MailType.PASSWORD_RESET) {
			mail = createPasswordResetMail(emailIds, template);
		}

		return mail;
	}

	private Mail createPasswordResetMail(String[] emailIds, MailTemplate template) {
		// Class cls = Class.forName(mailClass.className());
		String port = AppConfig.mailProp(MailProperties.SMTP_PORT);
		String[] toEmails = emailIds;
		String host = AppConfig.mailProp(MailProperties.HOST);
		String user = AppConfig.mailProp(MailProperties.USER);
		String password = AppConfig.mailProp(MailProperties.PASSWORD);

		return new PasswordResetMail(port, toEmails, host, user, password, (PasswordResetLinkTemplate) template);
	}
}
