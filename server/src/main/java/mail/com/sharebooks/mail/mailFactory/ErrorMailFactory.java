package com.sharebooks.mail.mailFactory;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.MailProperties;
import com.sharebooks.exception.IncorrectInstanceException;
import com.sharebooks.mail.entities.ErrorMail;
import com.sharebooks.mail.errorTemplates.ErrorTemplate;
import com.sharebooks.mail.templates.MailTemplate;
import com.sharebooks.mail.util.MailUtility;

public class ErrorMailFactory {
	private static ErrorMailFactory instance;

	private ErrorMailFactory() {

	}

	public static ErrorMailFactory instance() {

		if (instance == null) {
			synchronized (ErrorMailFactory.class) {
				if (instance == null) {
					instance = new ErrorMailFactory();
				}
			}
		}

		return instance;
	}

	public ErrorMail createMail(MailTemplate template) {
		if (!(template instanceof ErrorTemplate)) {
			throw new IncorrectInstanceException("Instance not of ErrorTemplate.");
		}

		String port = AppConfig.mailProp(MailProperties.SMTP_PORT);
		String toEmailsStr = AppConfig.mailProp(MailProperties.ERROR_TO_EMAILS);
		String[] toEmails = MailUtility.getEmailAddressesFromEmailString(toEmailsStr);
		String host = AppConfig.mailProp(MailProperties.HOST);
		String user = AppConfig.mailProp(MailProperties.USER);
		String password = AppConfig.mailProp(MailProperties.PASSWORD);

		return new ErrorMail(port, toEmails, host, user, password, (ErrorTemplate) template);
	}
}
