package com.sharebooks.sms.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.SmsProperties;
import com.sharebooks.sms.enums.SmsProvider;
import com.sharebooks.util.StringUtility;

public class SmsUtility {

	public static SmsProvider getSmsProvider() {
		int smsProviderId = Integer.parseInt(AppConfig.smsProp(SmsProperties.SMS_PROVIDER));
		SmsProvider smsProvider = SmsProvider.get(smsProviderId);
		return smsProvider;
	}

	public static boolean getSmsRequestSuccessStatus(String json) throws ParseException {
		JSONObject jo = (JSONObject) new JSONParser().parse(json);
		String status = (String) jo.get("status");
		if (!StringUtility.isEmptyOrNull(status)) {
			return status.equals("success");
		}
		return false;
	}
}
