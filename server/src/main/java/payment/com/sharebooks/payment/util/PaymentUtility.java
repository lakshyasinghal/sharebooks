package com.sharebooks.payment.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PaymentUtility {

	public static String getPaymentReqFromHttpRespJson(String json) throws Exception {
		Object obj = new JSONParser().parse(json);
		JSONObject jo = (JSONObject) obj;

		return jo.get("payment_request").toString();
	}
}
