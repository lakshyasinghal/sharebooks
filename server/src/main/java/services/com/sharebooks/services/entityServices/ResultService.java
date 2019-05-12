package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.BookDao;
import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.dtos.ResultDTO;
import com.sharebooks.dtos.enums.DTOType;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.exception.ExceptionType;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.factory.dtoFactory.ResultDTOFactory;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.sources.FactorySource;

public class ResultService extends AbstractService{
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(ResultService.class.getName());
	private final BookDao bookDao = (BookDao) DaoSource.getDao(EntityType.BOOK.desc());
	private final UserDao userDao = (UserDao) DaoSource.getDao(EntityType.USER.desc());
	private final ResultDTOFactory resultFactory = (ResultDTOFactory)FactorySource.getDTOFactory(DTOType.RESULT.desc());
	
	public ResultService(){
		synchronized(ResultService.class){
			if(instanceCount==1){
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}
	
	
	public ResultDTO getResult(String bookUid) throws SQLException,Exception{
		try{
			ResultDTO result = null;
			Book book = bookDao.getBook(bookUid);
			User user = null;
			if(book!=null){
				user = userDao.getUser(book.ownerUid());
			}
			if(user!=null){
				result = resultFactory.get(user, book);
			}
			return result;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	} 
	
	
	public List<ResultDTO> getSimilarResults(Book book) throws SQLException, Exception{
		if(book==null){
			throw new NullPointerException("Book is null");
		}
		List<ResultDTO> results = null;
		Map<Book,User> resultMap = new HashMap<Book,User>();
		List<Book> similarBooks = null;
		List<String> userUids = new ArrayList<String>();
		
		similarBooks = bookDao.getSimilarBooks(book);
		//removing the original book from the list
		similarBooks.remove(book);
		//get all the userUids from the books
		for(Book similarBook: similarBooks){
			userUids.add(similarBook.ownerUid());
		}
		
		List<User> users = userDao.getUsers(userUids);
		
		Comparator<Book> sortByUserUidComparator = new Comparator<Book>(){
			@Override
			public int compare(Book b1, Book b2){
				return b1.ownerUid().compareTo(b2.ownerUid());
			}
		};
		
		Collections.sort(similarBooks, sortByUserUidComparator);
		Collections.sort(users);
		
		for(int i=0,len=similarBooks.size();i<len;i++){
			resultMap.put(similarBooks.get(i),users.get(i));
		}
		
		results = resultFactory.getDTOsFromMap(resultMap);
		return results;
	}
	
//	public List<Book> getAllBooks() throws SQLException,Exception{
//		//LOGGER.entering("ResultService", "getAllBooks");
//		try{
//			List<Book> books = dao.getAllBooks();
//			//LOGGER.finer(books.toString());
//			//LOGGER.exiting("ResultService", "getAllBooks");
//			return books;
//		}
//		catch(SQLException ex){
//			sendExceptionMail(ExceptionType.SQL , ex);
//			LOGGER.debug(ex.getSQLState());
//			throw ex;
//		}
//		catch(Exception ex){
//			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
//			LOGGER.debug(ex.getMessage());
//			throw ex;
//		}
//	}
}
