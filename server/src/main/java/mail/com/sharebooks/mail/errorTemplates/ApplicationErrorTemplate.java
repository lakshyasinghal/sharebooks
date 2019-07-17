package com.sharebooks.mail.errorTemplates;

import com.sharebooks.mail.enums.Subject;

public class ApplicationErrorTemplate extends ErrorTemplate {
	private Exception appException;

	public ApplicationErrorTemplate(Exception appException) {
		super(Subject.INTERNAL_SERVER_ERROR.desc(), null, null);
		this.appException = appException;
	}

	public void prepareBody() {
		StringBuilder b = new StringBuilder();

		b.append("<h2 style=\"background-color:black;color: red\">");
		b.append(appException.getMessage());
		b.append("</h2>");

		body = b.toString();
	}

}
