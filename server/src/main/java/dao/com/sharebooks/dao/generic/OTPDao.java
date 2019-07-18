package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.List;

import com.sharebooks.entities.coreEntities.OneTimePassword;

public interface OTPDao extends Dao {

	public abstract boolean createOTP(OneTimePassword otp) throws SQLException, Exception;

	public abstract OneTimePassword getOtp(String uid) throws SQLException, Exception;

	public abstract OneTimePassword getOtpByUserUidAndOtpVal(String userUid, String otpVal)
			throws SQLException, Exception;

	public abstract boolean markAccepted(String uid) throws SQLException, Exception;

	public abstract boolean markRejected(String uid) throws SQLException, Exception;

	public abstract boolean markExpired(List<String> uids) throws SQLException, Exception;

}
