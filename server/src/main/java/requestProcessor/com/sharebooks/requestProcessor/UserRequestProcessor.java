package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.Preference;
import com.sharebooks.exception.InvalidOTPException;
import com.sharebooks.exception.InvalidUserException;
import com.sharebooks.exception.OTPExpiredException;
import com.sharebooks.exception.PasswordResetLinkExpiredException;
import com.sharebooks.exception.UsernameAlreadyExistsException;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.services.entityServices.UserService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

@SuppressWarnings("unchecked")
public class UserRequestProcessor extends AbstractRequestProcessor {
	private static UserRequestProcessor processor = new UserRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(UserRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final UserService userService = ServiceSource.getUserService();
	private final EntityFactory<User> factory = (EntityFactory<User>) FactorySource
			.getEntityFactory(EntityType.USER.desc());

	// private constructor to help make the class singleton
	private UserRequestProcessor() {

	}

	// get singleton instance of the class
	public static UserRequestProcessor getInstance() {
		return processor;
	}

	public String processLoginRequest(String username, String password) throws Exception {
		LOGGER.trace("Entering login in UserRequestProcessor");
		Map<String, Object> map = new HashMap<String, Object>();
		User user = null;
		boolean success = false;
		int statusCode = -1, errorCode = -1;
		try {
			user = userService.login(username, password);
			if (user == null) {
				statusCode = Status.INCORRECT_LOGIN_CREDENTIALS.id();
			} else {
				success = true;
				statusCode = Status.LOGIN_SUCCESSFUL.id();
				map.put("user", user);
			}
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		return response.process();
	}

	public String processCreateUserRequest(HttpServletRequest req) throws Exception {
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
			success = userService.createUser(user);
			if (success) {
				statusCode = Status.USER_CREATED_SUCCESSFULLY.id();
			} else {
				statusCode = Status.USER_NOT_CREATED.id();
			}
		} catch (UsernameAlreadyExistsException ex) {
			LOGGER.debug(ex.getMessage());
			statusCode = Status.USERNAME_ALREADY_EXISTS.id();
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		LOGGER.trace("Leaving processCreateRequest");
		return response.process();
	}

	public String processGetAllUsersRequest() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> users = null;
		boolean success = false;
		int statusCode = -1, errorCode = -1;
		try {
			users = userService.getAllUsers();
			if (users == null || users.size() == 0) {
				statusCode = Status.NO_USERS_EXIST.id();
			} else {
				statusCode = Status.FETCH_ALL_USERS_SUCCESSFUL.id();
				success = true;
			}
			map.put("users", users);
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		return response.process();
	}

	public String processGetUserRequest(String uid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try {
			user = userService.getUser(uid);
			if (user == null) {
				statusCode = Status.NO_RESULTS_FOUND.id();
			} else {
				statusCode = Status.FETCH_USER_BY_ID_SUCCESSFUL.id();
			}
			success = true;
			map.put("user", user);
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, map);
		return response.process();
	}

	public String processUpdateUserRequest(HttpServletRequest req) throws Exception {
		LOGGER.trace("Entering processUpdateRequest");
		boolean success = false;
		int statusCode = -1, errorCode = -1;
		String userJsonStr = null;
		User user = null;
		try {
			userJsonStr = getJsonFromRequest(req);
			user = factory.createFromJson(userJsonStr);
			success = userService.updateUser(user);
			if (success) {
				statusCode = Status.USER_UPDATED_SUCCESSFULLY.id();
			} else {
				statusCode = Status.USER_NOT_UPDATED.id();
			}

		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		LOGGER.trace("Leaving processUpdateRequest");
		return response.process();
	}

	public String processSavePreferencesRequest(String uid, HttpServletRequest req) throws Exception {
		LOGGER.trace("Entering processSavePreferencesRequest");
		boolean success = false;
		int statusCode = -1, errorCode = -1;
		String preferencesJson = null;
		List<Preference> preferences = null;
		try {
			preferencesJson = getJsonFromRequest(req);
			preferences = (List<Preference>) FactorySource.getEntityFactory(EntityType.PREFERENCE.desc())
					.getListFromJson(preferencesJson);
			success = userService.savePreferences(uid, preferences);
			if (success) {
				statusCode = Status.PREFERENCES_SAVED_SUCCESSFULLY.id();
			} else {
				statusCode = Status.PREFERENCES_NOT_SAVED.id();
			}
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		LOGGER.trace("Leaving processSavePreferencesRequest");
		return response.process();
	}

	public String processUpdateProfileRequest(String uid, String name, String username, String contactNo,
			String password) throws Exception {
		LOGGER.trace("Entering processUpdateProfileRequest");
		boolean success = false;
		int statusCode = -1, errorCode = -1;
		try {
			success = userService.updateProfile(uid, name, username, contactNo, password);
			if (success) {
				statusCode = Status.USER_PROFILE_UPDATED.id();
			} else {
				statusCode = Status.USER_PROFILE_NOT_UPDATED.id();
			}
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		LOGGER.trace("Leaving processUpdateProfileRequest");
		return response.process();
	}

	public String processSendOTP(String userUid, String contactNo) throws Exception {
		LOGGER.trace("Entering processSendOTP");
		boolean success = false;
		int statusCode = -1, errorCode = -1;
		try {
			success = userService.sendOTP(userUid, contactNo);
			if (success) {
				statusCode = Status.OTP_SENT_SUCCESSFULLY.id();
			}
		} catch (InvalidUserException ex) {
			statusCode = Status.INVALID_OTP_USER.id();
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		LOGGER.trace("Leaving processSendOTP");
		return response.process();
	}

	public String processVerifyOTP(String userUid, String otp) throws Exception {
		LOGGER.trace("Entering processVerifyOTP");
		boolean success = false;
		int statusCode = -1, errorCode = -1;
		try {
			userService.verifyOTP(userUid, otp);
			success = true;
			statusCode = Status.OTP_VERIFIED_SUCCESSFULLY.id();
		} catch (InvalidOTPException ex) {
			statusCode = Status.INVALID_OTP_ENTERED.id();
		} catch (OTPExpiredException ex) {
			statusCode = Status.OTP_EXPIRED.id();
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		LOGGER.trace("Leaving processVerifyOTP");
		return response.process();
	}

	public String processResetPassword(String linkUid, String userUid, String password) throws Exception {
		LOGGER.trace("Entering processUpdatePassword");
		boolean success = false;
		int statusCode = -1, errorCode = -1;
		try {
			success = userService.resetPassword(linkUid, userUid, password);
			statusCode = Status.PASSWORD_RESET_SUCCESSFULLY.id();
		} catch (PasswordResetLinkExpiredException ex) {
			statusCode = Status.PASSWORD_LINK_EXPIRED.id();
		} catch (Exception ex) {
			log(ex, LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success, statusCode, errorCode, null);
		LOGGER.trace("Leaving processUpdatePassword");
		return response.process();
	}
}
