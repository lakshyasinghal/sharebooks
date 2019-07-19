package com.sharebooks.sms.service;

import com.sharebooks.http.HttpClient;
import com.sharebooks.http.service.HttpService;
import com.sharebooks.sms.entities.SmsRequest;
import com.sharebooks.sms.enums.SmsProvider;
import com.sharebooks.sms.factory.SmsRequestFactory;
import com.sharebooks.sms.util.Utility;

public class SmsService {
	private static SmsService instance;
	private SmsRequestFactory smsRequestFactory = SmsRequestFactory.instance();
	private HttpService httpService = HttpService.instance();

	private SmsService() {

	}

	public static SmsService instance() {
		if (instance == null) {
			synchronized (SmsService.class) {
				if (instance == null) {
					instance = new SmsService();
				}
			}
		}

		return instance;
	}

	// will return success status
	public boolean sendSms(String message, String numbers) throws Exception {
		// get SmsRequest instance
		SmsProvider smsProvider = Utility.getSmsProvider();
		SmsRequest smsRequest = smsRequestFactory.create(smsProvider, message, numbers);

		// extract httpClient from SmsRequest
		HttpClient client = smsRequest.serializeAsHttp();

		// make request and get response in json format
		String data = httpService.makeRequest(client);

		// parse the json and return status
		return Utility.getSmsRequestSuccessStatus(data);
	}

}
