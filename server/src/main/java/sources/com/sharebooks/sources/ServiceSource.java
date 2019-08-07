package com.sharebooks.sources;

import java.util.HashMap;
import java.util.Map;

import com.sharebooks.geo.service.GeocodingService;
import com.sharebooks.payment.service.PaymentService;
import com.sharebooks.services.entityServices.BookCategoryService;
import com.sharebooks.services.entityServices.BookRequestService;
import com.sharebooks.services.entityServices.BookService;
import com.sharebooks.services.entityServices.CityService;
import com.sharebooks.services.entityServices.NotificationService;
import com.sharebooks.services.entityServices.OTPService;
import com.sharebooks.services.entityServices.OrderService;
import com.sharebooks.services.entityServices.QuoteService;
import com.sharebooks.services.entityServices.ResultService;
import com.sharebooks.services.entityServices.StateService;
import com.sharebooks.services.entityServices.UserService;
import com.sharebooks.sms.service.SmsService;
import com.sharebooks.sources.enums.ServiceType;

public class ServiceSource {
	private static Map<String, Object> serviceMap = new HashMap<String, Object>();

	public static void init() throws Exception {
		try {
			serviceMap.put(ServiceType.BOOK.desc(), new BookService());
			serviceMap.put(ServiceType.USER.desc(), new UserService());
			serviceMap.put(ServiceType.BOOK_REQUEST.desc(), new BookRequestService());
			serviceMap.put(ServiceType.NOTIFICATION.desc(), new NotificationService());
			serviceMap.put(ServiceType.ORDER.desc(), new OrderService());
			serviceMap.put(ServiceType.QUOTE.desc(), new QuoteService());
			serviceMap.put(ServiceType.RESULT.desc(), new ResultService());
			serviceMap.put(ServiceType.CITY.desc(), new CityService());
			serviceMap.put(ServiceType.STATE.desc(), new StateService());
			serviceMap.put(ServiceType.BOOK_CATEGORY.desc(), new BookCategoryService());
			serviceMap.put(ServiceType.GEOCODING.desc(), GeocodingService.instance());
			serviceMap.put(ServiceType.OTP.desc(), OTPService.instance());
			serviceMap.put(ServiceType.PAYMENT.desc(), PaymentService.instance());
			serviceMap.put(ServiceType.SMS.desc(), SmsService.instance());
		} catch (Exception ex) {
			throw ex;
		}
	}

	public static Object service(String serviceName) {
		return serviceMap.get(serviceName);
	}
}
