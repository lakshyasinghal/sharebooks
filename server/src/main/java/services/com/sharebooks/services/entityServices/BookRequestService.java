package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.BookRequestDao;
import com.sharebooks.entities.coreEntities.BookRequest;
import com.sharebooks.entities.coreEntities.Notification;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.coreEntities.enums.NotificationStatus;
import com.sharebooks.entities.coreEntities.enums.NotificationType;
import com.sharebooks.exception.CacheException;
import com.sharebooks.exception.ExceptionType;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.factory.entityFactory.NotificationFactory;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.util.dateTime.LocalDateTime;

public class BookRequestService extends EntityService {
	// instanceCount varaible will help in replicating the singleton
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(BookRequestService.class.getName());
	private final BookRequestDao dao = (BookRequestDao) DaoSource.getDao(EntityType.BOOK_REQUEST.desc());

	public BookRequestService() {
		synchronized (BookRequestService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	// uid is the user uid which will be used to fetch both received book
	// requests and sent book requests
	public List<List<BookRequest>> getBookRequestsByUid(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering getBookRequestsByUid");
		// the outer list will contain two lists of book requests
		// at 0 we will have received book requests list and at 1 sent book
		// requests list
		List<List<BookRequest>> bookRequestListList = new ArrayList<List<BookRequest>>();
		List<BookRequest> receivedBookRequests = dao.getBookRequestsByBookOwnerUid(uid);
		List<BookRequest> sentBookRequests = dao.getBookRequestsByRequesterUid(uid);
		bookRequestListList.add(receivedBookRequests);
		bookRequestListList.add(sentBookRequests);
		LOGGER.trace("Leaving getBookRequestsByUid");
		return bookRequestListList;
	}

	// create book request method with book request argument
	public boolean createBookRequest(BookRequest bookRequest) throws SQLException, Exception {
		LOGGER.trace("Entering createBookRequest");
		NotificationFactory nf = (NotificationFactory) FactorySource.getEntityFactory(EntityType.NOTIFICATION.desc());
		Notification notification = nf.create(bookRequest.bookOwnerUid(), NotificationType.BOOK_REQUEST,
				bookRequest.uid(), null);
		boolean created = dao.createBookRequest(bookRequest, bookRequest.uid(), notification);
		LOGGER.trace("Leaving createBookRequest");
		return created;
	}

	public boolean updateBookRequest(BookRequest bookRequest) throws SQLException, Exception {
		LOGGER.trace("Entering updateBookRequest");
		boolean updated = dao.updateBookRequest(bookRequest);
		LOGGER.trace("Leaving updateBookRequest");
		return updated;
	}

	public boolean acceptBookRequest(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering acceptBookRequest");
		BookRequest bookRequest = dao.getBookRequest(uid);
		NotificationFactory notifFactory = (NotificationFactory) FactorySource
				.getEntityFactory(EntityType.NOTIFICATION.desc());
		Notification notification = notifFactory.create(bookRequest.requesterUid(),
				NotificationType.BOOK_REQUEST_ACCEPTANCE, bookRequest.uid(), null);
		boolean accepted = dao.acceptBookRequest(bookRequest.uid(), notification);
		LOGGER.trace("Leaving acceptBookRequest");
		return accepted;
	}

	public boolean rejectBookRequest(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering rejectBookRequest");
		BookRequest bookRequest = dao.getBookRequest(uid);
		NotificationFactory nf = (NotificationFactory) FactorySource.getEntityFactory(EntityType.NOTIFICATION.desc());
		Notification notification = nf.create(bookRequest.requesterUid(), NotificationType.BOOK_REQUEST_REJECTION,
				bookRequest.uid(), null);
		boolean rejected = dao.rejectBookRequest(bookRequest.uid(), bookRequest.bookUid(), notification);
		LOGGER.trace("Leaving rejectBookRequest");
		return rejected;
	}
}
