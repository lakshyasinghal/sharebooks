package com.sharebooks.mail.service;

import org.apache.log4j.Logger;

import com.sharebooks.mail.entities.Mail;
import com.sharebooks.mail.enums.MailType;
import com.sharebooks.mail.errorTemplates.ErrorTemplate;
import com.sharebooks.mail.mailFactory.MailFactory;
import com.sharebooks.mail.templateFactory.ErrorTemplateFactory;
import com.sharebooks.mail.templateFactory.TemplateFactory;
import com.sharebooks.mail.templates.MailTemplate;

public class MailService {
	private static final Logger LOGGER = Logger.getLogger(MailService.class);
	private static MailService instance = null;
	private ErrorTemplateFactory errorTemplateFactory = ErrorTemplateFactory.instance();
	private TemplateFactory templateFactory = TemplateFactory.instance();
	private MailFactory mailFactory = MailFactory.instance();

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

	public void sendPasswordResetLink(String emailId, String linkUrl) throws Exception {
		try {
			LOGGER.debug("Sending password reset mail.");
			MailTemplate template = templateFactory.create(MailType.PASSWORD_RESET, linkUrl);
			template.prepareBody();
			String[] emailIds = { emailId };
			Mail passwordresetMail = mailFactory.createMail(MailType.PASSWORD_RESET, template, emailIds);
			passwordresetMail.send();
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void sendErrorMail(Exception ex) throws Exception {
		try {
			LOGGER.debug("Sending error mail.");
			ErrorTemplate template = (ErrorTemplate) errorTemplateFactory.create(ex);
			template.prepareBody();
			Mail errorMail = mailFactory.createMail(MailType.ERROR, template, null);
			errorMail.send();
		} catch (Exception e) {
			throw e;
		}
	}

}
