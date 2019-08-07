package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.entities.helperEntities.BookCategory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.services.entityServices.BookCategoryService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;
import com.sharebooks.sources.enums.ServiceType;

public class BookCategoryRequestProcessor extends AbstractRequestProcessor {
	private static BookCategoryRequestProcessor processor = new BookCategoryRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(BookCategoryRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final BookCategoryService service = (BookCategoryService) ServiceSource
			.service(ServiceType.BOOK_CATEGORY.desc());

	// private constructor to help make the class singleton
	private BookCategoryRequestProcessor() {

	}

	// get singleton instance of the class
	public static BookCategoryRequestProcessor getInstance() {
		return processor;
	}

	public String processGetBookCategoriesRequest() throws Exception {
		LOGGER.trace("Entered processGetBookCategoriesRequest");
		Map<String, Object> map = new HashMap<String, Object>();
		List<BookCategory> bookCategories = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try {
			bookCategories = service.getBookCategories();
			if (bookCategories != null) {
				success = true;
				statusCode = Status.FETCH_BOOK_CATEGORIES_SUCCESSFUL.id();
				map.put("bookCategories", bookCategories);
			} else {
				statusCode = Status.BOOK_CATEGORIES_COULD_NOT_BE_FETCHED.id();
			}
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}

		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		LOGGER.trace("Leaving processGetBookCategoriesRequest");
		return response.process();
	}
}
