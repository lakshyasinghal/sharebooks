package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.AbstractOTPDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlInsertQueryProcessor;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.SqlUpdateQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.OTPQueries;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.entities.coreEntities.OneTimePassword;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;

public class OTPSqlDao extends AbstractOTPDao {
	private static final Logger LOGGER = Logger.getLogger(OTPSqlDao.class);
	// private EntityFactory<User> factory;
	private final Database database = Database.SHAREBOOKS_USER_ACCOUNTS;
	private final Table table = Table.ONE_TIME_PASSWORDS;
	private final OTPQueries otpQueries = OTPQueries.instance();

	@Override
	public boolean createOTP(OneTimePassword otp) throws SQLException, Exception {
		Map<String, Object> otpMap = otp.map();
		// remove id field and id value from map as it won't be required in insert query
		otpMap.remove("id");
		SqlQuery query = new SqlInsertQuery(table.desc(), otpMap);
		query.build();
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:" + rowsAffected);
		// LOGGER.exiting("UserSqlDao", "insertUser");
		return rowsAffected > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OneTimePassword getOtp(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering getOtp");
		LOGGER.trace("uid:" + uid);
		OneTimePassword otp = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		SqlQuery query = new SqlReadQuery(table.desc(), map);
		query.build();
		List<Entity> entityList = (List<Entity>) queryProcessor.processReadQuery(database.desc(), query.toString(),
				EntityType.ONE_TIME_PASSWORD);
		List<OneTimePassword> otps = convertIntoOTPList(entityList);
		if (otps != null && otps.size() > 0) {
			otp = otps.get(0);
		}
		LOGGER.trace("Leaving getOtp");
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
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		SqlQuery query = new SqlReadQuery(table.desc(), map);
		query.build();
		List<Entity> entityList = (List<Entity>) queryProcessor.processReadQuery(database.desc(), query.toString(),
				EntityType.ONE_TIME_PASSWORD);
		List<OneTimePassword> otps = convertIntoOTPList(entityList);
		if (otps != null && otps.size() > 0) {
			otp = otps.get(0);
		}
		LOGGER.trace("Leaving getOtpByUserUidAndOtpVal");
		return otp;
	}

	@Override
	public boolean markAccepted(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering markAccepted");
		String query = otpQueries.queryForMarkAccepted(uid);
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query);
		return rowsAffected > 0 ? true : false;
	}

	@Override
	public boolean markExpired(List<String> uids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean markRejected(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering markRejected");
		String query = otpQueries.queryForMarkRejected(uid);
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query);
		return rowsAffected > 0 ? true : false;
	}

}
