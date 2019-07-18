package com.sharebooks.mail.entities;

import com.sharebooks.mail.templates.PasswordResetLinkTemplate;

public class PasswordResetMail extends GenericMail {

	public PasswordResetMail() {

	}

	public PasswordResetMail(String port, String[] toEmails, String host, String user, String password,
			PasswordResetLinkTemplate template) {
		super(port, toEmails, template.subject(), template.body(), template.contentType(), host, user, password);
	}
}
