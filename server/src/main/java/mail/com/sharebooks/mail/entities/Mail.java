package com.sharebooks.mail.entities;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface Mail {

	public void send() throws AddressException, MessagingException;
}
