package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.OTPDao;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.OTPQueries;
import com.sharebooks.entities.coreEntities.OneTimePassword;
import com.sharebooks.entities.coreEntities.enums.EntityType;

public class OTPSqlDao extends AbstractSqlDao implements OTPDao {
	private static final Logger LOGGER = Logger.getLogger(OTPSqlDao.class);
	// private EntityFactory<User> factory;
	private final Database database = Database.USER_ACCOUNTS;
	private final Table table = Table.ONE_TIME_PASSWORDS;
	private final OTPQueries otpQueries = OTPQueries.instance();

	@Override
	public boolean createOTP(OneTimePassword otp) throws SQLException, Exception {
		return super.create(otp, database, table);
	}

	@Override
	public OneTimePassword getOtp(String uid) throws SQLException, Exception {
		LOGGER.trace("Entered getOtp");
		OneTimePassword otp = null;
		try {
			otp = (OneTimePassword) super.getByUid(uid, database, table, EntityType.ONE_TIME_PASSWORD);
		} catch (Exception ex) {
		}
		return otp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OneTimePassword getOtpByUserUidAndOtpVal(String userUid, String otpVal) throws SQLException, Exception {
		LOGGER.trace("Entering getOtpByUserUidAndOtpVal");
		LOGGER.debug("userUid:" + userUid);
		LOGGER.debug("otpVal:" + otpVal);
		OneTimePassword otp = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userUid", userUid);
		map.put("otp", otpVal);
		try {
			otp = (OneTimePassword) super.getFirst(map, database, table, EntityType.ONE_TIME_PASSWORD);
		} catch (Exception ex) {
		}
		LOGGER.trace("Leaving getOtpByUserUidAndOtpVal");
		return otp;
	}

	@Override
	public boolean markAccepted(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering markAccepted");
		String queryStr = otpQueries.queryForMarkAccepted(uid);
		return super.updateUsingQuery(queryStr, database, table);
	}

	@Override
	public boolean markExpired(List<String> uids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean markRejected(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering markRejected");
		String queryStr = otpQueries.queryForMarkRejected(uid);
		return super.updateUsingQuery(queryStr, database, table);
	}

}
