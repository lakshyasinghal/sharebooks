package com.sharebooks.requestProcessor;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.sharebooks.coreEntities.Quote;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.factory.entityFactory.QuoteFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Error;
import com.sharebooks.response.Response;
import com.sharebooks.response.Status;
import com.sharebooks.services.entityServices.QuoteService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

public class QuoteRequestProcessor {
	private static QuoteRequestProcessor instance = new QuoteRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(QuoteRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final QuoteService quoteService = ServiceSource.getQuoteService();

	private QuoteRequestProcessor() {

	}

	// get singleton instance of the class
	public static QuoteRequestProcessor getInstance() {
		return instance;
	}

	public String processCreateQuoteRequest(String bookUid, String userUid) throws Exception {
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Map<String, Object> map = new HashMap<String, Object>();
		String quoteUid = null;
		Response response = null;
		try {
			QuoteFactory quoteFactory = (QuoteFactory) FactorySource.getEntityFactory(EntityType.Quote.desc());
			Quote quote = quoteFactory.create(bookUid, userUid);
			success = quoteService.createQuote(quote);
			if (success) {
				quoteUid = quote.uid();
				map.put("quoteUid", quoteUid);
				statusCode = Status.QUOTE_CREATED_SUCCESSFULLY.id();
			} else {
				statusCode = Status.QUOTE_NOT_CREATED.id();
			}
		} catch (SQLException ex) {
			LOGGER.error("SQLException", ex);
			errorCode = Error.DATABASE_ERROR.id();
		} catch (Exception ex) {
			LOGGER.error("Exception", ex);
			errorCode = Error.GENERAL_EXCEPTION.id();
		}

		response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		return response.process();
	}
	
	public String processUpdateQuoteRequest(String uid, String bookUid) throws Exception {
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Response response = null;
		try {
			success = quoteService.updateQuote(uid, bookUid);
			if (success) {
				statusCode = Status.QUOTE_UPDATED_SUCCESSFULLY.id();
			} else {
				statusCode = Status.QUOTE_NOT_UPDATED.id();
			}
		} catch (SQLException ex) {
			LOGGER.error("SQLException", ex);
			errorCode = Error.DATABASE_ERROR.id();
		} catch (Exception ex) {
			LOGGER.error("Exception", ex);
			errorCode = Error.GENERAL_EXCEPTION.id();
		}

		response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		return response.process();
	}
}
