package com.sharebooks.mail.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService2 {
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public static void main(String args[]) throws AddressException, MessagingException {
		try {
			MailService2 javaEmail = new MailService2();

			javaEmail.setMailServerProperties();
			javaEmail.createEmailMessage();
			javaEmail.sendEmail();

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public void setMailServerProperties() {

		String emailPort = "465";// gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "false");
		emailProperties.put("mail.smtp.starttls.enable", "false");

	}

	public void createEmailMessage() throws AddressException, MessagingException {
		String[] toEmails = { "lakshyasinghal333@gmail.com" };
		String emailSubject = "I am going to do it.";
		String emailBody = "Lakshya you are going to do it!";

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");// for a html email
		// emailMessage.setText(emailBody);// for a text email

	}

	public void sendEmail() throws AddressException, MessagingException {

		String emailHost = "smtpout.secureserver.net";
		String fromUser = "info@sharebooks.in";
		String fromUserEmailPassword = "d-WVvsQuZq2A6R8";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}
}
