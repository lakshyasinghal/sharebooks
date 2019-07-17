package com.sharebooks.mail.util;

public class MailUtility {

	public static String[] getEmailAddressesFromEmailString(String emailsStr) {
		String[] emails = emailsStr.split(",");
		for (int i = 0, l = emails.length; i < l; i++) {
			emails[i] = emails[i].trim();
		}

		return emails;
	}
}
