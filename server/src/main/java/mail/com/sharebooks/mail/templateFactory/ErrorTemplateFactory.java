package com.sharebooks.mail.templateFactory;

import com.sharebooks.mail.errorTemplates.ApplicationErrorTemplate;
import com.sharebooks.mail.errorTemplates.ErrorTemplate;

public class ErrorTemplateFactory {
	private static ErrorTemplateFactory instance;

	private ErrorTemplateFactory() {

	}

	public static ErrorTemplateFactory instance() {
		if (instance == null) {
			synchronized (ErrorTemplateFactory.class) {
				if (instance == null) {
					instance = new ErrorTemplateFactory();
				}
			}
		}

		return instance;
	}

	public ErrorTemplate getErrorTemplate(Exception ex) {
		return new ApplicationErrorTemplate(ex);
	}
}
