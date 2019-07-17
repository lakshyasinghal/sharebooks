package com.sharebooks.mail.errorTemplates;

import com.sharebooks.mail.enums.MailBodyContentType;
import com.sharebooks.mail.templates.GenericMailTemplate;

public class ErrorTemplate extends GenericMailTemplate {

	public ErrorTemplate() {

	}

	public ErrorTemplate(String subject, String body, MailBodyContentType contentType) {
		super(subject, body, contentType);
	}

	@Override
	public void prepareBody() {
		// TODO Auto-generated method stub
	}

}
