package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.cache.Cache;
import com.sharebooks.coreEntities.User;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.exception.ExceptionType;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.exception.UsernameAlreadyExistsException;
import com.sharebooks.helperEntities.Preference;
import com.sharebooks.sources.CacheSource;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.util.JsonUtility;
import com.sharebooks.util.PasswordUtils;

@SuppressWarnings("unchecked")
public class UserService extends EntityService{
	//instanceCount varaible will help in replicating the singleton 
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private final Cache<User> cache = (Cache<User>) CacheSource.getCache(EntityType.USER.desc());
	private final UserDao dao = (UserDao) DaoSource.getDao(EntityType.USER.desc());
	
	public UserService(){
		synchronized(UserService.class){
			if(instanceCount==1){
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}
	
	
	
	public User login(String username,String password) throws Exception{
		User user = null;
		try {
			String encPass = PasswordUtils.encryptPassword(password);
			user = dao.getUserByUsernameAndPassword(username,encPass);
		} catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
		return user;
	}
	
	
	public boolean createUser(User user) throws Exception{
		LOGGER.trace("Entering createUser");
		try{
			User existingUser = null; 
			boolean created = false;
			existingUser = dao.getByUsername(user.username());
			if(existingUser!=null){
				throw new UsernameAlreadyExistsException();
			}
			else{
				String password = user.password();
				User userWithEncPass = user.cloneWithNewPassword(PasswordUtils.encryptPassword(password));
				created = dao.createUser(userWithEncPass);
				LOGGER.trace("Leaving createUser");
				return created;
			}
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
	
	public boolean updateProfile(String uid, String name, String username, String contactNo, String password) throws Exception{
		LOGGER.trace("Entering updateProfile");
		try{
			boolean updated = false;
			password = PasswordUtils.encryptPassword(password);
			updated = dao.updateProfile(uid, name, username, contactNo, password);
			LOGGER.trace("Leaving updateProfile");
			return updated;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
	
	public List<User> getAllUsers() throws Exception{
		try{
			List<User> users = dao.getAllUsers();
			return users;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
	
	public User getUser(String uid) throws Exception{
		try{
			User user = (User)cache.get(uid);
			if(user == null){
				user = dao.getUser(uid);
				cache.insert(uid, user);
			}
			return user;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public boolean updateUser(User user) throws Exception{
		LOGGER.trace("Entering updateUser");
		boolean updated = false;
		try{
			updated = dao.updateUser(user);
			LOGGER.trace("Leaving updateUser");
			return updated;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
	
	public boolean savePreferences(String uid, List<Preference> preferences) throws Exception{
		LOGGER.trace("Entering savePreferences");
		boolean updated = false;
		try{
			updated = dao.savePreferences(uid, JsonUtility.getJsonArrayFromList(preferences).toJSONString());
			LOGGER.trace("Leaving savePreferences");
			return updated;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
}
