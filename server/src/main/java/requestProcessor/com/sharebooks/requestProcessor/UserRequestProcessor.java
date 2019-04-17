package com.sharebooks.requestProcessor;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.sharebooks.coreEntities.User;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.exception.CacheException;
import com.sharebooks.exception.UsernameAlreadyExistsException;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Error;
import com.sharebooks.response.Response;
import com.sharebooks.response.Status;
import com.sharebooks.services.entityServices.UserService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

@SuppressWarnings("unchecked")
public class UserRequestProcessor extends AbstractRequestProcessor{
	private static UserRequestProcessor processor = new UserRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(UserRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final UserService userService = ServiceSource.getUserService();
	private final EntityFactory<User> factory = (EntityFactory<User>) FactorySource.getEntityFactory(EntityType.USER.desc());
	
	//private constructor to help make the class singleton
	private UserRequestProcessor(){
		
	}
	
	//get singleton instance of the class
	public static UserRequestProcessor getInstance(){
		return processor;
	}
	
	
	public String processLoginRequest(String username , String password) throws Exception{
		LOGGER.trace("Entering login in UserRequestProcessor");
		Map<String,Object> map = new HashMap<String,Object>();
		User user = null;
		boolean success = false;
		int statusCode=-1,errorCode=-1;
		try{
			user = userService.login(username,password);
			if(user==null){
				statusCode=Status.INCORRECT_LOGIN_CREDENTIALS.id();
			}
			else{
				success=true;
				statusCode=Status.LOGIN_SUCCESSFUL.id();
			}
		}catch(SQLException ex){
			errorCode = Error.DATABASE_ERROR.id();
			LOGGER.debug("");
		}
		catch(Exception ex){
			errorCode = Error.GENERAL_EXCEPTION.id();
			LOGGER.debug("");
		}
		
		map.put("user",user);
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		
		return response.process();
	}
	
	public String processCreateRequest(HttpServletRequest req) throws Exception{
		LOGGER.trace("Entering processCreateRequest");
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Response response = null;
		String userJsonStr = null;
		User user = null;
		try {
			userJsonStr = getJsonFromRequest(req);
			user = factory.createFromJson(userJsonStr);
		}catch(Exception ex){
			LOGGER.debug(ex);
			statusCode = Status.SOMETHING_WENT_WRONG.id();
			errorCode = Error.INPUT_JSON_READ_ERROR.id();
			response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
			return response.process();
		}

		
		try{
			success = userService.createUser(user);
			statusCode = Status.BOOK_CREATED_SUCCESSFULLY.id();
		}
		catch(UsernameAlreadyExistsException ex){
			LOGGER.debug(ex.getMessage());
			statusCode = Status.USERNAME_ALREADY_EXISTS.id();
		}
		catch(SQLException ex){
			LOGGER.debug(ex);
			errorCode = Error.DATABASE_ERROR.id();
		}
		catch(Exception ex){
			LOGGER.debug(ex);
			errorCode = Error.GENERAL_EXCEPTION.id();
		}
		
		response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
		LOGGER.trace("Leaving processCreateRequest");
		return response.process();
	}
	
	
	public String processGetAllRequest() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		List<User> users = null;
		boolean success = false;
		int statusCode = -1,errorCode = -1;
		try{
			users = userService.getAllUsers();
			if(users==null || users.size()==0){
				statusCode = Status.NO_USERS_EXIST.id();
			}
			else{
				statusCode = Status.FETCH_ALL_USERS_SUCCESSFUL.id();
			}
			success = true;
		}
		catch(CacheException ex){
			success = false;
			errorCode = Error.CACHE_ERROR.id();
		}
		catch(SQLException ex){
			success = false;
			errorCode = Error.DATABASE_ERROR.id();
		}
		catch(Exception ex){
			success = false;
			errorCode = Error.GENERAL_EXCEPTION.id();
		}
		map.put("users", users);
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		return response.process();
	}
	
	
	public String processGetByIdRequest(String id) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		User user = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			if(id==null || id.equals("")){
				errorCode = Error.ID_NOT_AVAILABLE_IN_REQUEST.id();
			}
			else{
				user = userService.getUserById(Integer.parseInt(id));
				if(user==null){
					statusCode = Status.NO_RESULTS_FOUND.id();
				}
				else{
					statusCode = Status.FETCH_USER_BY_ID_SUCCESSFUL.id();
				}
				success = true;
			}
		}
		catch(CacheException ex){
			success = false;
			errorCode = Error.CACHE_ERROR.id();
		}
		catch(SQLException ex){
			success = false;
			errorCode = Error.DATABASE_ERROR.id();
		}
		catch(Exception ex){
			success = false;
			errorCode = Error.GENERAL_EXCEPTION.id();
		}
		map.put("user", user);
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		return response.process();
	}
	
	public String processUpdateRequest(HttpServletRequest req) throws Exception{
		LOGGER.trace("Entering processUpdateRequest");
		boolean success = false;
		int statusCode = -1,errorCode = -1;
		String userJsonStr = null;
		User user = null;
		try{
			userJsonStr = getJsonFromRequest(req);
			user = factory.createFromJson(userJsonStr);
			success = userService.updateUser(user);
			statusCode = Status.USER_UPDATED_SUCCESSFULLY.id();
		}
		catch(CacheException ex){
			LOGGER.error("CacheException :",ex);
			success = false;
			errorCode = Error.CACHE_ERROR.id();
		}
		catch(SQLException ex){
			LOGGER.error("SQLException :",ex);
			success = false;
			errorCode = Error.DATABASE_ERROR.id();
		}
		catch(Exception ex){
			LOGGER.error("Exception :",ex);
			success = false;
			errorCode = Error.GENERAL_EXCEPTION.id();
		}

		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
		LOGGER.trace("Leaving processUpdateRequest");
		return response.process();
	}
}










