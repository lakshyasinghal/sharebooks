package com.sharebooks.requestProcessor.requestValidators;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import com.sharebooks.entities.coreEntities.Book;

public class BookRequestValidator extends RequestValidator {
	private static BookRequestValidator instance = new BookRequestValidator();

	private BookRequestValidator() {

	}

	public static BookRequestValidator getInstance() {
		return instance;
	}

	public ValidationMessage validateGetBookRequest(String uid) {
		try {
			return new ValidationMessage();
		} catch (Exception ex) {
			throw new ValidationException();
		}
	}

	public ValidationMessage validateGetBooksBySearchTermRequest(String searchTerm) throws Exception {
		try {
			return null;
		} catch (Exception ex) {
			throw new ValidationException();
		}
	}

	public ValidationMessage validateGetBooksByCategoryRequest(String category) throws Exception {
		try {
			return null;
		} catch (Exception ex) {
			throw new ValidationException();
		}
	}

	// validate insert book request
	public ValidationMessage validateCreateRequest(Book book) throws Exception {
		try {
			return null;
		} catch (Exception ex) {
			throw new ValidationException();
		}
	}

	public ValidationMessage validateDeleteBookRequest(String uid) throws Exception {
		try {
			return null;
		} catch (Exception ex) {
			throw new ValidationException();
		}
	}

	// to be implemented later
	public ValidationMessage validateUpdateRequest(HttpServletRequest req) throws Exception {
		try {
			return null;
		} catch (Exception ex) {
			throw new ValidationException();
		}
	}

	// to be implemented later
	public ValidationMessage validateUploadImageSrcRequest(String id, String imgSrc) throws Exception {
		try {
			return null;
		} catch (Exception ex) {
			throw new ValidationException();
		}
	}
}
