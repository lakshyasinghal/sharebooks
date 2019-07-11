package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.factory.entityFactory.QuoteFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.payment.entities.PaymentRequestWebhook;
import com.sharebooks.payment.factory.PaymentRequestWebhookFactory;
import com.sharebooks.payment.services.PaymentService;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.sources.FactorySource;

public class PaymentRequestProcessor extends AbstractRequestProcessor {
	private static PaymentRequestProcessor instance = new PaymentRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(PaymentRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final PaymentService paymentService = new PaymentService();
	private final PaymentRequestWebhookFactory webhookFactory = PaymentRequestWebhookFactory.instance();

	QuoteFactory quoteFactory = (QuoteFactory) FactorySource.getEntityFactory(EntityType.Quote.desc());

	private PaymentRequestProcessor() {

	}

	// get singleton instance of the class
	public static PaymentRequestProcessor getInstance() {
		return instance;
	}

	// will return response containing payment url for Subscription
	public String processSubscriptionPaymentURL(String userUid) throws Exception {
		LOGGER.trace("Entering processSubscriptionPaymentUrlRequest");
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Map<String, Object> map = new HashMap<String, Object>();
		Response response = null;
		try {
			String longurl = paymentService.subscriptionPaymentURLRequest(userUid);
			if (longurl != null) {
				success = true;
				map.put("longurl", longurl);
				statusCode = Status.FETCH_SUBSCRIPTION_PAYMENT_URL_SUCCESSFUL.id();
			} else {
				statusCode = Status.FETCH_SUBSCRIPTION_PAYMENT_URL_FAILED.id();
			}
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}

		response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		LOGGER.trace("Leaving processSubscriptionPaymentUrlRequest");
		return response.process();
	}

	// only the http status code returned will be important in the response
	public String processUpdateSubscriptionPaymentStatus(HttpServletRequest req) throws Exception {
		LOGGER.trace("Entering processUpdatePaymentStatusRequest");
		boolean success = false;
		Response response = null;
		int errorCode = -1;
		PaymentRequestWebhook paymentRequestWebhook = webhookFactory.createFromHttpRequest(req);
		try {
			success = paymentService.updateSubscriptionPaymentStatus(paymentRequestWebhook);
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}

		response = responseFactory.getJsonResponse(success, -1, errorCode, null);
		LOGGER.trace("Leaving processUpdatePaymentStatusRequest");
		return response.process();
	}

}
