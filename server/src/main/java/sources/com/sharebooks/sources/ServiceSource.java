package com.sharebooks.sources;

import com.sharebooks.services.entityServices.BookRequestService;
import com.sharebooks.services.entityServices.BookService;
import com.sharebooks.services.entityServices.MiscService;
import com.sharebooks.services.entityServices.NotificationService;
import com.sharebooks.services.entityServices.OrderService;
import com.sharebooks.services.entityServices.QuoteService;
import com.sharebooks.services.entityServices.ResultService;
import com.sharebooks.services.entityServices.UserService;

public class ServiceSource {
	private static BookService bookService;
	private static UserService userService;
	private static BookRequestService bookRequestService;
	private static NotificationService notificationService;
	private static MiscService miscService;
	private static OrderService orderService;
	private static QuoteService quoteService;
	private static ResultService resultService;

	public static void init() throws Exception {
		try {
			initBookService();
			initUserService();
			initBookRequestService();
			initNotificationService();
			initMiscService();
			initOrderService();
			initQuoteService();
			initResultService();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private static void initBookService() throws Exception {
		try {
			// Cache<Book> bookCache = (Cache<Book>)
			// CacheSource.getCache(EntityType.BOOK.desc());
			// BookDao dao = (BookDao)DaoSource.getDao("book");
			// EntityFactory<Book> factory = BookFactory.getInstance();
			// BookService.init(bookCache , dao);
			bookService = new BookService();
		} catch (Exception ex) {
			throw ex;
		}
	}

	private static void initUserService() throws Exception {
		try {
			// Cache<User> userCache = (Cache<User>)
			// CacheSource.getCache(EntityType.USER.desc());
			// UserDao dao = (UserDao)DaoSource.getDao("user");
			// UserService.init(userCache , dao);
			userService = new UserService();
		} catch (Exception ex) {
			throw ex;
		}
	}

	// not using cache with book requests
	private static void initBookRequestService() throws Exception {
		try {
			// Cache<User> userCache = (Cache<User>)
			// CacheSource.getCache(EntityType.USER.desc());
			// BookRequestDao dao =
			// (BookRequestDao)DaoSource.getDao(EntityType.BOOK_REQUEST.desc());
			// BookRequestService.init(dao);
			bookRequestService = new BookRequestService();
		} catch (Exception ex) {
			throw ex;
		}
	}

	// not using cache with book requests
	private static void initNotificationService() throws Exception {
		try {
			// Cache<User> userCache = (Cache<User>)
			// CacheSource.getCache(EntityType.USER.desc());
			// BookRequestDao dao =
			// (BookRequestDao)DaoSource.getDao(EntityType.BOOK_REQUEST.desc());
			// BookRequestService.init(dao);
			notificationService = new NotificationService();
		} catch (Exception ex) {
			throw ex;
		}
	}

	// not using cache with book requests
	private static void initMiscService() throws Exception {
		try {
			// Cache<User> userCache = (Cache<User>)
			// CacheSource.getCache(EntityType.USER.desc());
			// MiscDao dao = (MiscDao)DaoSource.getDao("miscellaneous");
			// BookRequestService.init(dao);
			miscService = new MiscService();
			miscService.init();
		} catch (Exception ex) {
			throw ex;
		}
	}

	// not using cache with book requests
	private static void initOrderService() throws Exception {
		try {
			orderService = new OrderService();
		} catch (Exception ex) {
			throw ex;
		}
	}

	// not using cache with book requests
	private static void initQuoteService() throws Exception {
		try {
			quoteService = new QuoteService();
		} catch (Exception ex) {
			throw ex;
		}
	}

	// not using cache with book requests
	private static void initResultService() throws Exception {
		try {
			resultService = new ResultService();
		} catch (Exception ex) {
			throw ex;
		}
	}

	public static BookService getBookService() {
		return bookService;
	}

	public static UserService getUserService() {
		return userService;
	}

	public static BookRequestService getBookRequestService() {
		return bookRequestService;
	}

	public static NotificationService getNotificationService() {
		return notificationService;
	}

	public static MiscService getMiscService() {
		return miscService;
	}

	public static OrderService getOrderService() {
		return orderService;
	}

	public static QuoteService getQuoteService() {
		return quoteService;
	}
	
	public static ResultService getResultService() {
		return resultService;
	}
}
