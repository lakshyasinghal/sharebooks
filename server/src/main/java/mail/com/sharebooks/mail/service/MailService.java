package com.sharebooks.mail.service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailService {

	public static void main(String[] args) {
		MailService ms = new MailService();
		ms.method4();
	}

	public void sendMail() {
		String to = "lakshyasinghal333@gmail.com"; // change accordingly
		String from = "lakshyasinghal333@gmail.com"; // change accordingly
		String host = "127.0.0.1"; // or IP address
		// String host = "smtp.gmail.com";

		// Get the session object
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.port", "25");
		Session session = Session.getDefaultInstance(properties, null);

		// compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Ping");
			message.setText("Hello, this is example of sending email  ");

			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void method2() {
		String from = "lakshyasinghal333@gmail.com";
		String to = "lakshyasinghal333@gmail.com";
		String subject = "Test mail";
		String message = "The mail service is working correctly.";
		String login = "lakshyasinghal333@gmail.com";
		String password = "10103035nitj";

		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Authenticator auth = new Authenticator() {
		// };

		Session session = Session.getDefaultInstance(props);

		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setText(message);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(msg);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

	public void method3() {
		// change accordingly
		final String username = "lakshyasinghal333@gmail.com";

		// change accordingly
		final String password = "10103035nitj";

		// or IP address
		final String host = "localhost";

		// Get system properties
		Properties props = new Properties();

		// enable authentication
		props.put("mail.smtp.auth", host);

		// enable STARTTLS
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "false");
		// Setup mail server
		props.put("mail.smtp.host", "smtp.gmail.com");

		// TLS Port
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props);

		// creating Session instance referenced to
		// Authenticator object to pass in
		// Session.getInstance argument
		// Session session = Session.getInstance(props,
		// new javax.mail.Authenticator() {
		//
		// //override the getPasswordAuthentication method
		// protected PasswordAuthentication
		// getPasswordAuthentication() {
		//
		// return new PasswordAuthentication(username,
		// password);
		// }
		// });

		try {

			// compose the message
			// javax.mail.internet.MimeMessage class is
			// mostly used for abstraction.
			Message message = new MimeMessage(session);

			// header field of the header.
			message.setFrom(new InternetAddress("lakshyasinghal333@gmail.com"));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("lakshyasinghal333@gmail.com"));
			message.setSubject("Hello");
			message.setText("Yo it has been sent");

			Transport.send(message); // send Message

			System.out.println("Done");

		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

	public void method4() {
		// change accordingly
		String to = "lakshyasinghal333@gmail.com";

		// change accordingly
		String from = "lakshyasinghal333@gmail.com";

		// or IP address
		String host = "localhost";

		// mail id
		final String username = "lakshyasinghal333@gmail.com";

		// correct password for gmail id
		final String password = "10103035nitj";

		System.out.println("TLSEmail Start");
		// Get the session object

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// SSL Port
		properties.put("mail.smtp.port", "465");

		// enable authentication
		properties.put("mail.smtp.auth", "true");

		// SSL Factory
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// creating Session instance referenced to
		// Authenticator object to pass in
		// Session.getInstance argument
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

			// override the getPasswordAuthentication
			// method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("username", "password");
			}
		});

		// compose the message
		try

		{
			// javax.mail.internet.MimeMessage class is mostly
			// used for abstraction.
			MimeMessage message = new MimeMessage(session);

			// header field of the header.
			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Fuck you");
			message.setText("Hello, badass is sending email ");

			// Send message
			Transport.send(message);
			System.out.println("Yo it has been sent..");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
