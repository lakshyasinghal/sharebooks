package com.sharebooks.mail.entities;

import com.sharebooks.mail.errorTemplates.ErrorTemplate;

public class ErrorMail extends GenericMail {

	public ErrorMail() {

	}

	public ErrorMail(String port, String[] toEmails, String host, String user, String password,
			ErrorTemplate errorTemplate) {
		super(port, toEmails, errorTemplate.subject(), errorTemplate.body(), errorTemplate.contentType(), host, user,
				password);
	}
}
