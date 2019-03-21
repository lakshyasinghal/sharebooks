package com.sharebooks.services.entityServices;

import java.util.List;
import com.sharebooks.cache.Cache;
import com.sharebooks.coreEntities.User;
import com.sharebooks.dao.generic.UserDao;

public class UserService{
	private static UserService userService;
	private final Cache<User> cache;
	private final UserDao dao;
	
	private UserService(Cache<User> cache , UserDao dao){
		this.cache = cache;
		this.dao = dao;
	}
	
	public static void init(Cache<User> cache , UserDao dao){
		if(userService == null){
			userService = new UserService(cache , dao);
		}	
	}
	
	public static UserService getInstance(){
		return userService;
	}
	
	
	public List<User> getAllUsers() throws Exception{
		try{
			List<User> books = dao.getAllUsers();
			return books;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public User getUserById(int id) throws Exception{
		try{
			User book = (User)cache.get(id);
			if(book == null){
				book = dao.getUserById(id);
				cache.insert(id, book);
			}
			return book;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}