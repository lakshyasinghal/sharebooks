package com.sharebooks.mail.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public static void main(String args[]) throws AddressException, MessagingException {
		try {
			MailService javaEmail = new MailService();

			// javaEmail.setMailServerProperties();
			// javaEmail.createEmailMessage();
			// javaEmail.sendEmail();
			javaEmail.whole();

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public void whole() throws AddressException, MessagingException {
		String emailPort = "587";// gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

		String[] toEmails = { "lakshyasinghal333@gmail.com" };
		String emailSubject = "Test Email";
		String emailBody = "Hahahahahahahaha";

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");

		String emailHost = "smtp.gmail.com";
		String fromUser = "lakshyasinghal33"; // just the id alone without @gmail.com
		String fromUserEmailPassword = "nitj10103035";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}
}
