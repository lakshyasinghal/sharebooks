package com.sharebooks.payment.services;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.sharebooks.appConfig.AppConfig;
import com.sharebooks.dao.generic.PaymentDao;
import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.http.HttpClient;
import com.sharebooks.http.service.HttpService;
import com.sharebooks.payment.entities.PaymentRequest;
import com.sharebooks.payment.entities.PaymentRequestWebhook;
import com.sharebooks.payment.entities.PaymentRequest_Request;
import com.sharebooks.payment.enums.PaymentType;
import com.sharebooks.payment.factory.PaymentRequestFactory;
import com.sharebooks.payment.factory.PaymentRequest_RequestFactory;
import com.sharebooks.payment.util.PaymentUtility;
import com.sharebooks.properties.PaymentProperties;
import com.sharebooks.sources.DaoSource;

public class PaymentService {
	private static Logger LOGGER = Logger.getLogger(PaymentService.class);
	private static int instanceCount = 0;
	private final UserDao userDao = (UserDao) DaoSource.getDao(EntityType.USER.desc());
	private final PaymentDao paymentDao = (PaymentDao) DaoSource.getDao("payment");
	// private PaymentFactory paymentFactory = PaymentFactory.getInstance();
	private HttpService httpService = HttpService.instance();
	private PaymentRequestFactory paymentRequestFactory = PaymentRequestFactory.instance();

	public PaymentService() {
		synchronized (PaymentService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	// will return a long url pertaining to the payment request created
	public String registrationPaymentURLRequest(String userUid) throws SQLException, Exception {
		User user = userDao.getUser(userUid);
		String amount = AppConfig.paymentProp(PaymentProperties.REGISTRATION_AMOUNT);
		String purpose = PaymentType.REGISTRATION.desc();

		// get payment request using factory
		PaymentRequest_Request paymentRequest_Request = PaymentRequest_RequestFactory.getInstance()
				.getPaymentRequest(amount, purpose, user.name(), user.username(), user.contactNo());

		// make http request
		HttpClient httpClient = paymentRequest_Request.serializeAsHttp();

		// make request and get http response data
		String data = httpService.makeRequest(httpClient);

		// get the payment request response from http response
		PaymentRequest paymentRequest = paymentRequestFactory
				.createFromJson(PaymentUtility.getPaymentReqFromHttpRespJson(data));
		// store the response in db

		// obtain the payment url and return
		return paymentRequest.longurl();
	}

	public boolean updatePaymentStatus(PaymentRequestWebhook paymentRequestWebhook) throws SQLException, Exception {
		String payment_id = paymentRequestWebhook.paymentId();
		String email = paymentDao.getUserEmailByPaymentId(payment_id);

		return false;
	}
}
