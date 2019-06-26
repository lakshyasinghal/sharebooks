package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.factory.entityFactory.QuoteFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.payment.services.PaymentService;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.sources.FactorySource;

public class PaymentRequestProcessor extends AbstractRequestProcessor {
	private static PaymentRequestProcessor instance = new PaymentRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(PaymentRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final PaymentService paymentService = new PaymentService();

	QuoteFactory quoteFactory = (QuoteFactory) FactorySource.getEntityFactory(EntityType.Quote.desc());

	private PaymentRequestProcessor() {

	}

	// get singleton instance of the class
	public static PaymentRequestProcessor getInstance() {
		return instance;
	}

	public String processRegistrationPaymentURLRequest(String userUid) throws Exception {
		LOGGER.trace("Entering processRegistrationPaymentUrlRequest");
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Map<String, Object> map = new HashMap<String, Object>();
		Response response = null;
		try {
			String longurl = paymentService.registrationPaymentURLRequest(userUid);
			if (longurl != null) {
				success = true;
				map.put("longurl", longurl);
				statusCode = Status.FETCH_REGISTRATION_PAYMENT_URL_SUCCESSFUL.id();
			} else {
				statusCode = Status.FETCH_REGISTRATION_PAYMENT_URL_FAILED.id();
			}
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}

		response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		LOGGER.trace("Leaving processRegistrationPaymentUrlRequest");
		return response.process();
	}

	public String processUpdatePaymentStatusRequest(HttpServletRequest req) throws Exception {
		LOGGER.trace("Entering processUpdatePaymentStatusRequest");
		boolean success = false;
		Map<String, Object> map = new HashMap<String, Object>();
		Response response = null;
		try {
			String longurl = paymentService.registrationPaymentURLRequest(userUid);
			if (longurl != null) {
				success = true;
				map.put("longurl", longurl);
				statusCode = Status.FETCH_REGISTRATION_PAYMENT_URL_SUCCESSFUL.id();
			} else {
				statusCode = Status.FETCH_REGISTRATION_PAYMENT_URL_FAILED.id();
			}
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}

		response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		LOGGER.trace("Leaving processRegistrationPaymentUrlRequest");
		return response.process();
	}

}
