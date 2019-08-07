package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sharebooks.dtos.ResultDTO;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.services.entityServices.ResultService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;
import com.sharebooks.sources.enums.ServiceType;

@SuppressWarnings("unchecked")
public class ResultRequestProcessor extends AbstractRequestProcessor {
	private static ResultRequestProcessor processor = new ResultRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(ResultRequestProcessor.class.getName());
	private final EntityFactory<Book> bookFactory = (EntityFactory<Book>) FactorySource
			.getEntityFactory(EntityType.BOOK.desc());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final ResultService resultService = (ResultService) ServiceSource.service(ServiceType.RESULT.desc());
//	private final DTOFactory<ResultDTO> resultfactory = (DTOFactory<ResultDTO>) FactorySource.getDTOFactory(DTOType.RESULT.desc());

	// private constructor to help make the class singleton
	private ResultRequestProcessor() {

	}

	// get singleton instance of the class
	public static ResultRequestProcessor getInstance() {
		return processor;
	}

	public String processGetResultRequest(String bookUid) throws Exception {
		// LOGGER.entering("ResultRequestProcessor", "processGetAllBooksRequest");
		Map<String, Object> map = new HashMap<String, Object>();
		ResultDTO result = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try {
			result = resultService.getResult(bookUid);
			if (result != null) {
				success = true;
				statusCode = Status.FETCH_RESULT_SUCCESSFUL.id();
				map.put("result", result);
			} else {
				statusCode = Status.FETCH_RESULT_FAILED.id();
			}
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}

		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		// LOGGER.exiting("ResultRequestProcessor", "processGetAllBooksRequest");
		return response.process();
	}

	// process insert book request
	public String processGetSimilarResultsRequest(HttpServletRequest req) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Response response = null;
		String bookJsonStr = null;
		Book book = null;
		List<ResultDTO> results = null;
		try {
			bookJsonStr = getJsonFromRequest(req);
			book = bookFactory.createFromJson(bookJsonStr);
			results = resultService.getSimilarResults(book);
			if (results != null) {
				success = true;
				statusCode = Status.FETCH_SIMILAR_RESULTS_SUCCESSFUL.id();
				map.put("results", results);
			} else {
				statusCode = Status.FETCH_SIMILAR_RESULTS_FAILED.id();
			}
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}

		response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		return response.process();
	}

}
