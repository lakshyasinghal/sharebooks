package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.sharebooks.dtos.SummaryInfo;
import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.factory.entityFactory.QuoteFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.services.entityServices.QuoteService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

public class QuoteRequestProcessor extends AbstractRequestProcessor{
	private static QuoteRequestProcessor instance = new QuoteRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(QuoteRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final QuoteService quoteService = ServiceSource.getQuoteService();
	QuoteFactory quoteFactory = (QuoteFactory) FactorySource.getEntityFactory(EntityType.QUOTE.desc());

	private QuoteRequestProcessor() {

	}

	// get singleton instance of the class
	public static QuoteRequestProcessor getInstance() {
		return instance;
	}
	
	
	public String processGetQuoteRequest(String uid) throws Exception {
		LOGGER.trace("Entering processGetQuoteRequest");
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Map<String, Object> map = new HashMap<String, Object>();
		Response response = null;
		try {
			Quote quote = quoteService.getQuote(uid);
			if (quote!=null) {
				success = true;
				map.put("quote", quote);
				statusCode = Status.FETCH_QUOTE_SUCCESSFUL.id();
			} else {
				statusCode = Status.FETCH_QUOTE_FAILED.id();
			}
		} 
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}

		response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		LOGGER.trace("Leaving processGetQuoteRequest");
		return response.process();
	}

	public String processCreateQuoteRequest(HttpServletRequest req) throws Exception {
		LOGGER.trace("Entering processCreateQuoteRequest");
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Map<String, Object> map = new HashMap<String, Object>();
		String jsonStr = null;
		String quoteUid = null;
		Response response = null;
		try {
			jsonStr = getJsonFromRequest(req);
			Quote quote = quoteFactory.createFromJson(jsonStr);
			success = quoteService.createQuote(quote);
			if (success) {
				quoteUid = quote.uid();
				map.put("quoteUid", quoteUid);
				statusCode = Status.QUOTE_CREATED_SUCCESSFULLY.id();
			} else {
				statusCode = Status.QUOTE_NOT_CREATED.id();
			}
		} 
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		LOGGER.trace("Leaving processCreateQuoteRequest");
		return response.process();
	}
	
	public String processUpdateQuoteRequest(HttpServletRequest req) throws Exception {
		LOGGER.trace("Entering processUpdateQuoteRequest");
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		String jsonStr = null;
		Response response = null;
		try {
			jsonStr = getJsonFromRequest(req);
			Quote quote = quoteFactory.createFromJson(jsonStr);
			success = quoteService.updateQuote(quote);
			if (success) {
				statusCode = Status.QUOTE_UPDATED_SUCCESSFULLY.id();
			} else {
				statusCode = Status.QUOTE_NOT_UPDATED.id();
			}
		} 
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		LOGGER.trace("Leaving processUpdateQuoteRequest");
		return response.process();
	}
	
	
	public String processGetSummaryRequest(String uid) throws Exception {
		LOGGER.trace("Entering processGetSummaryRequest");
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Map<String, Object> map = new HashMap<String, Object>();
		Response response = null;
		try {
			SummaryInfo summaryInfo = quoteService.getSummary(uid);
			if (summaryInfo!=null) {
				success = true;
				map.put("summaryInfo", summaryInfo);
				statusCode = Status.FETCH_SUMMARY_SUCCESSFUL.id();
			} else {
				statusCode = Status.FETCH_SUMMARY_FAILED.id();
			}
		} 
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		LOGGER.trace("Leaving processGetSummaryRequest");
		return response.process();
	}
	
	
	public String processConfirmQuoteRequest(String uid) throws Exception {
		LOGGER.trace("Entering processConfirmQuoteRequest");
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Response response = null;
		try {
			success = quoteService.confirmQuote(uid);
			if (success) {
				statusCode = Status.QUOTE_CONFIRMED.id();
			} else {
				statusCode = Status.QUOTE_NOT_CONFIRMED.id();
			}
		} 
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		LOGGER.trace("Leaving processConfirmQuoteRequest");
		return response.process();
	}
}
