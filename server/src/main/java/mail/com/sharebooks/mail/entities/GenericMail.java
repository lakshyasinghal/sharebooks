package com.sharebooks.mail.entities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sharebooks.mail.enums.MailBodyContentType;

public abstract class GenericMail implements Mail {
	private String port;
	private String[] toEmails;
	private String subject;
	private String body;
	private MailBodyContentType contentType;
	private String host;
	private String user;
	private String password;

	public GenericMail() {

	}

	@Override
	public void send() throws AddressException, MessagingException {
		// String emailPort = "587";// gmail's smtp port

		Properties emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", port);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

		// String[] toEmails = { "lakshyasinghal333@gmail.com" };
		// String emailSubject = "Test Email";
		// String emailBody = "Hahahahahahahaha";

		Session mailSession = Session.getDefaultInstance(emailProperties, null);
		MimeMessage emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(subject);
		emailMessage.setContent(body, contentType.desc());

		// String emailHost = "smtp.gmail.com";
		// String emailHost = host;
		// String fromUser = user;
		// String fromUser = "lakshyasinghal33"; // just the id alone without @gmail.com
		// String fromUserEmailPassword = "nitj10103035";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(host, user, password);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}
}
