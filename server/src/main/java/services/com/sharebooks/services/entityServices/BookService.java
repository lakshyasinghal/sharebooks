package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.w3c.dom.Entity;

import com.sharebooks.cache.Cache;
import com.sharebooks.dao.generic.BookDao;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.exception.*;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.sources.CacheSource;
import com.sharebooks.sources.DaoSource;

@SuppressWarnings("unchecked")
public class BookService extends EntityService {
	// instanceCount varaible will help in replicating the singleton
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());
	private final Cache<Book> cache = (Cache<Book>) CacheSource.getCache(EntityType.BOOK.desc());
	private final BookDao dao = (BookDao) DaoSource.getDao(EntityType.BOOK.desc());

	public BookService() {
		synchronized (BookService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	// needs to be implemented
	public List<Book> getBooks() throws SQLException, Exception {
		try {
			return null;
		} catch (Exception ex) {
			sendExceptionMail(ExceptionType.UNIDENTIFIED, ex);
			throw ex;
		}
	}

	public List<Book> getAllBooks() throws SQLException, Exception {
		List<Book> books = dao.getAllBooks();
		return books;
	}

	public Book getBook(String uid) throws CacheException, SQLException, Exception {
		Book book = null;
		book = cache.get(uid);
		if (book == null) {
			book = dao.getBook(uid);
			if (book != null) {
				cache.insert(uid, book);
			}
		}
		return book;
	}

	public List<Book> getBooksBySearchTerm(String searchTerm) throws Exception {
		List<Book> books = dao.getBooksBySearchTerm(searchTerm);
		return books;
	}

	public List<Book> getBooksByCategory(String category) throws Exception {
		List<Book> books = dao.getBooksByCategory(category);
		return books;
	}

	// insert book method with book argument
	public boolean createBook(Book book) throws SQLException, Exception {
		boolean inserted = dao.createBook(book);
		return inserted;
	}

	public boolean updateBook(Book book) throws SQLException, Exception {
		LOGGER.trace("Entering updateBook");
		boolean updated = false;
		updated = dao.updateBook(book);
		LOGGER.trace("Leaving updateBook");
		return updated;
	}


	public boolean deleteBooks() throws SQLException, Exception {
		return false;
	}

	public boolean deleteBook(String uid) throws SQLException, Exception {
		return false;
	}
}
