package com.sharebooks.services.entityServices;

import org.apache.log4j.Logger;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.BookDao;
import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.sources.DaoSource;

public class OrderService extends EntityService{
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
	private final BookDao bookDao = (BookDao) DaoSource.getDao(EntityType.BOOK.desc());
	private final UserDao userDao = (UserDao) DaoSource.getDao(EntityType.USER.desc());
	
	public OrderService(){
		synchronized(OrderService.class){
			if(instanceCount==1){
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}
	
	

}
