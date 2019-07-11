package com.sharebooks.mail.entities;

import com.sharebooks.mail.enums.MailBodyContentType;

public interface MailTemplate {

	public String subject();

	public String body();

	public MailBodyContentType contentType();

	public void prepareBody();
}
