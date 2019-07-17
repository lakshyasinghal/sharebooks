package com.sharebooks.mail.templates;

import com.sharebooks.mail.enums.MailBodyContentType;

public abstract class GenericMailTemplate implements MailTemplate {
	protected String subject;
	protected String body;
	protected MailBodyContentType contentType = MailBodyContentType.HTML; // default will be html

	public GenericMailTemplate() {
	}

	public GenericMailTemplate(String subject, String body, MailBodyContentType contentType) {
		this.subject = subject;
		this.body = body;
		if(contentType!=null) {
			this.contentType = contentType;
		}
	}

	public String subject() {
		return subject;
	}

	public String body() {
		return body;
	}

	public MailBodyContentType contentType() {
		return contentType;
	}
}
