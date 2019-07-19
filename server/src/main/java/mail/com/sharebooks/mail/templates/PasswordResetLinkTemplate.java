package com.sharebooks.mail.templates;

import com.sharebooks.mail.enums.MailBodyContentType;
import com.sharebooks.mail.enums.Subject;

public class PasswordResetLinkTemplate extends GenericMailTemplate {
	private String url;

	public PasswordResetLinkTemplate(String url) {
		super(Subject.PASSWORD_RESET_LINK.desc(), null, MailBodyContentType.HTML);
		this.url = url;
	}

	@Override
	public void prepareBody() {
		StringBuilder b = new StringBuilder();

		b.append("<div style=\"background-color:lightblue;\">");
		b.append("<h2 style=\"color:white;\">Click the below url to change your password</h2>");
		b.append("<a style=\"color:black;text-decoration:underline\" href=" + url + ">" + url + "</a>");
		b.append("</div>");

		body = b.toString();
	}

}
