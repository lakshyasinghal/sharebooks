package com.sharebooks.payment.factory;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sharebooks.payment.entities.GenericPaymentRequest;
import com.sharebooks.payment.entities.GenericPaymentRequest.GenericPaymentRequestBuilder;
import com.sharebooks.payment.entities.PaymentRequest;

public class PaymentRequestFactory {
	private static Logger LOGGER = Logger.getLogger(PaymentRequestFactory.class);
	private static PaymentRequestFactory instance = null;

	private PaymentRequestFactory() {

	}

	public static PaymentRequestFactory instance() {
		if (instance == null) {
			synchronized (PaymentRequestFactory.class) {
				if (instance == null) {
					instance = new PaymentRequestFactory();
				}
			}
		}
		return instance;
	}

//	public Book createFromResultSet(ResultSet rs) throws FactoryException, Exception {
//
//		int id = rs.getInt("id");
//		String uid = rs.getString("uid");
//		String title = rs.getString("title");
//		String author = rs.getString("author");
//		String category = rs.getString("category");
//		String subcategory = rs.getString("subcategory");
//		int pages = rs.getInt("pages");
//		String ownerUid = rs.getString("ownerUid");
//		String imgSrc = rs.getString("imgSrc");
//		AvailableStatus available = AvailableStatus.valueOf(rs.getInt("available"));
//		AvailableStatus buy = AvailableStatus.valueOf(rs.getInt("buy"));
//		AvailableStatus rent = AvailableStatus.valueOf(rs.getInt("rent"));
//		int buyAmount = rs.getInt("buyAmount");
//		int rentAmount = rs.getInt("rentAmount");
//		String creationTimeStr = (rs.getTimestamp("creationTime")).toString();
//		LocalDateTime creationTime = LocalDateTime.buildFromString(creationTimeStr);
//		String lastModificationTimeStr = (rs.getTimestamp("lastModificationTime")).toString();
//		LocalDateTime lastModificationTime = LocalDateTime.buildFromString(lastModificationTimeStr);
//
//		Book book = new Book(id, uid, title, author, category, subcategory, pages, ownerUid, imgSrc, available, buy,
//				rent, buyAmount, rentAmount, creationTime, lastModificationTime);
//		return book;
//	}

	public PaymentRequest createFromJson(String json) throws Exception {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject) obj;

			String id = (String) jo.get("id");
			String phone = (String) jo.get("phone");
			String email = (String) jo.get("email");
			String buyer_name = (String) jo.get("buyer_name");
			String purpose = (String) jo.get("purpose");
			String status = (String) jo.get("status");
			boolean send_sms = (boolean) jo.get("send_sms");
			boolean send_email = (boolean) jo.get("send_email");
			String sms_status = (String) jo.get("sms_status");
			String email_status = (String) jo.get("email_status");
			String shorturl = (String) jo.get("shorturl");
			String longurl = (String) jo.get("longurl");
			String redirect_url = (String) jo.get("redirect_url");
			String webhook = (String) jo.get("webhook");
			String created_at = (String) jo.get("created_at");
			String modified_at = (String) jo.get("modified_at");
			boolean allow_repeated_payments = (boolean) jo.get("allow_repeated_payments");

			GenericPaymentRequestBuilder builder = new GenericPaymentRequest.GenericPaymentRequestBuilder();
			builder.id(id).phone(phone).email(email).buyerName(buyer_name).purpose(purpose).status(email_status)
					.sendSMS(send_sms).sendEmail(send_email).smsStatus(sms_status).emailStatus(email_status)
					.shortUrl(shorturl).longUrl(longurl).redirectUrl(redirect_url).webhook(webhook)
					.createdAt(created_at).modifiedAt(modified_at).allowRepeatedPayments(allow_repeated_payments);

			return builder.build();
		} catch (ParseException ex) {
			throw ex;
		}
	}
}
