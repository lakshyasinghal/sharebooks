package com.sharebooks.mail.service;

import org.apache.log4j.Logger;

import com.sharebooks.mail.entities.Mail;
import com.sharebooks.mail.enums.MailType;
import com.sharebooks.mail.errorTemplates.ErrorTemplate;
import com.sharebooks.mail.mailFactory.MailFactory;
import com.sharebooks.mail.templateFactory.ErrorTemplateFactory;

public class MailService {
	private static final Logger LOGGER = Logger.getLogger(MailService.class);
	private static MailService instance = null;
	private ErrorTemplateFactory errorTemplateFactory = ErrorTemplateFactory.instance();
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

	public void sendErrorMail(Exception ex) {
		try {
			LOGGER.debug("Sending error mail.");
			ErrorTemplate template = (ErrorTemplate) errorTemplateFactory.getErrorTemplate(ex);
			template.prepareBody();
			Mail errorMail = mailFactory.createMail(MailType.ERROR, template);
			errorMail.send();
		} catch (Exception e) {

		}
	}

}
