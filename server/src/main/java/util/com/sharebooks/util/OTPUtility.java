package com.sharebooks.util;

import java.util.Random;

import org.apache.log4j.Logger;

import com.sharebooks.util.dateTime.LocalDateTime;

public class OTPUtility {
	private static final Logger LOGGER = Logger.getLogger(OTPUtility.class);
	private static String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String Small_chars = "abcdefghijklmnopqrstuvwxyz";
	private static String numbers = "0123456789";
	private static String symbols = "!@#$%^&*_=+-/.?<>)";
	private static String values = Capital_chars + Small_chars + numbers + symbols;

	public static String getOTP(int otpLen) {
		LOGGER.debug("Generating new OTP");
		// Using random method
		Random rndm_method = new Random();

		char[] otp = new char[otpLen];
		for (int i = 0; i < otpLen; i++) {
			otp[i] = values.charAt(rndm_method.nextInt(values.length()));
		}
		return new String(otp);
	}

	// et refers to expiryTime
	public static boolean isExpired(LocalDateTime et) {
		LocalDateTime now = new LocalDateTime();
		boolean isExpired = false;

		if (now.year() == et.year()) {
			if (now.month() == et.month()) {
				if (now.date() == et.date()) {
					if (now.hour() == et.hour()) {
						if (now.min() == et.min()) {
							if (now.sec() == et.sec()) {

							} else if (now.sec() > et.sec()) {
								isExpired = true;
							}
						} else if (now.min() > et.min()) {
							isExpired = true;
						}
					} else if (now.hour() > et.hour()) {
						isExpired = true;
					}
				} else if (now.date() > et.date()) {

				}
			} else if (now.month() > et.month()) {
				isExpired = true;
			}
		} else if (now.year() > et.year()) {
			isExpired = true;
		}

		return isExpired;
	}

	public static void main(String[] args) {
		System.out.println(getOTP(5));

	}
}
