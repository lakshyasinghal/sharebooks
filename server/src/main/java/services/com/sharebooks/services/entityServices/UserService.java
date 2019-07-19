package com.sharebooks.services.entityServices;

import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.cache.Cache;
import com.sharebooks.dao.generic.OTPDao;
import com.sharebooks.dao.generic.PasswordResetLinkDao;
import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.entities.coreEntities.OneTimePassword;
import com.sharebooks.entities.coreEntities.PasswordResetLink;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.Preference;
import com.sharebooks.exception.InvalidOTPException;
import com.sharebooks.exception.InvalidUserException;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.exception.OTPExpiredException;
import com.sharebooks.exception.PasswordResetLinkExpiredException;
import com.sharebooks.exception.UsernameAlreadyExistsException;
import com.sharebooks.factory.entityFactory.OTPFactory;
import com.sharebooks.factory.entityFactory.PasswordResetLinkFactory;
import com.sharebooks.mail.service.MailService;
import com.sharebooks.sms.service.SmsService;
import com.sharebooks.sources.CacheSource;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.util.JsonUtility;
import com.sharebooks.util.OTPUtility;
import com.sharebooks.util.PasswordUtility;
import com.sharebooks.util.SmsUtility;

@SuppressWarnings("unchecked")
public class UserService extends EntityService {
	// instanceCount varaible will help in replicating the singleton
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private final MailService mailService = MailService.instance();
	private final SmsService smsService = SmsService.instance();
	private final Cache<User> cache = (Cache<User>) CacheSource.getCache(EntityType.USER.desc());
	private final UserDao userDao = (UserDao) DaoSource.getDao(EntityType.USER.desc());
	private final OTPDao otpDao = (OTPDao) DaoSource.getDao(EntityType.ONE_TIME_PASSWORD.desc());
	private final PasswordResetLinkDao linkDao = (PasswordResetLinkDao) DaoSource
			.getDao(EntityType.PASSWORD_RESET_LINK.desc());

	public UserService() {
		synchronized (UserService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	public User login(String username, String password) throws Exception {
		User user = null;
		String encPass = PasswordUtility.encryptPassword(password);
		user = userDao.getUserByUsernameAndPassword(username, encPass);
		return user;
	}

	public boolean createUser(User user) throws Exception {
		LOGGER.trace("Entering createUser");
		User existingUser = null;
		boolean created = false;
		existingUser = userDao.getByUsername(user.username());
		if (existingUser != null) {
			throw new UsernameAlreadyExistsException();
		} else {
			String password = user.password();
			User userWithEncPass = user.cloneWithNewPassword(PasswordUtility.encryptPassword(password));
			created = userDao.createUser(userWithEncPass);
			LOGGER.trace("Leaving createUser");
			return created;
		}
	}

	public boolean updateProfile(String uid, String name, String username, String contactNo, String password)
			throws Exception {
		LOGGER.trace("Entering updateProfile");
		boolean updated = false;
		password = PasswordUtility.encryptPassword(password);
		updated = userDao.updateProfile(uid, name, username, contactNo, password);
		LOGGER.trace("Leaving updateProfile");
		return updated;
	}

	public List<User> getAllUsers() throws Exception {
		List<User> users = userDao.getAllUsers();
		return users;
	}

	public User getUser(String uid) throws Exception {
		User user = (User) cache.get(uid);
		if (user == null) {
			user = userDao.getUser(uid);
			cache.insert(uid, user);
		}
		return user;
	}

	public boolean updateUser(User user) throws Exception {
		LOGGER.trace("Entering updateUser");
		boolean updated = false;
		updated = userDao.updateUser(user);
		LOGGER.trace("Leaving updateUser");
		return updated;
	}

	public boolean savePreferences(String uid, List<Preference> preferences) throws Exception {
		LOGGER.trace("Entering savePreferences");
		boolean updated = false;
		updated = userDao.savePreferences(uid, JsonUtility.getJsonArrayFromList(preferences).toJSONString());
		LOGGER.trace("Leaving savePreferences");
		return updated;
	}

	// the userUid and contactNo combination will ensure the authenticity of the
	// user
	public boolean sendOTP(String userUid, String contactNo) throws Exception {
		LOGGER.trace("Entered sendOTP");
		// check if record with given userUid and contactNo exists
		User user = userDao.getUserByUidAndContact(userUid, contactNo);
		if (user != null) {
			throw new InvalidUserException();
		}

		// if yes, create a new otp entry in the otp table and send the otp value
		OneTimePassword otp = OTPFactory.getInstance().create(userUid);
		otpDao.createOTP(otp);

		String otpMessage = SmsUtility.getOTPMessage(otp.otp());
		LOGGER.debug(otpMessage);
		smsService.sendSms(otpMessage, contactNo);
		LOGGER.trace("Leaving sendOTP");
		return true;
	}

	public void verifyOTP(String userUid, String otpVal) throws Exception {
		OneTimePassword otp = otpDao.getOtpByUserUidAndOtpVal(userUid, otpVal);
		// if otp is null, then the otp value entered by user is invalid
		if (otp == null) {
			throw new InvalidOTPException();
		}
		// check whether the otp is expired or not
		boolean isExpired = otp.isExpired() || OTPUtility.isExpired(otp.expiryTime());
		if (isExpired) {
			throw new OTPExpiredException();
		}

		// mark accepted in otp table and create new password reset link and send mail
		otpDao.markAccepted(otp.uid());
		User user = userDao.getUser(userUid);
		String longUrl = getLongUrlForPasswordReset();
		String shortUrl = getShortUrl(longUrl);
		createPasswordResetLink(otp.uesrUid(), longUrl, shortUrl);
		mailService.sendPasswordResetLink(user.username(), shortUrl);
	}

	public boolean resetPassword(String linkUid, String userUid, String newPassword) throws Exception {
		String encodedPassword = PasswordUtility.encryptPassword(newPassword);
		PasswordResetLink link = linkDao.get(linkUid);
		boolean isExpired = link.isExpired() || OTPUtility.isExpired(link.expiryTime());
		if (isExpired) {
			throw new PasswordResetLinkExpiredException();
		}
		boolean updated = userDao.updatePassword(userUid, encodedPassword);
		return updated;
	}

	private String getLongUrlForPasswordReset() {
		return "";
	}

	private String getShortUrl(String longUrl) {
		return longUrl;
	}

	private PasswordResetLink createPasswordResetLink(String userUid, String longUrl, String shortUrl)
			throws Exception {
		PasswordResetLink resetLink = PasswordResetLinkFactory.instance().create(userUid, longUrl, shortUrl);
		boolean created = linkDao.create(resetLink);
		return resetLink;
	}

}
