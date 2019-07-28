package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;

import com.sharebooks.entities.coreEntities.OneTimePassword;
import com.sharebooks.entities.coreEntities.OneTimePassword.OneTimePasswordBuilder;
import com.sharebooks.util.OTPUtility;
import com.sharebooks.util.dateTime.LocalDateTime;

public class OTPFactory implements EntityFactory<OneTimePassword> {
	private static OTPFactory instance = new OTPFactory();

	private OTPFactory() {
	}

	public static OTPFactory getInstance() {
		return instance;
	}

	@Override
	public OneTimePassword createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OneTimePassword createFromResultSet(ResultSet rs) throws Exception {
		int id = rs.getInt("id");
		String uid = rs.getString("uid");
		String userUid = rs.getString("userUid");
		String otp = rs.getString("otp");
		boolean accepted = rs.getBoolean("accepted");
		int attempts = rs.getInt("attempts");
		LocalDateTime generatedAt = LocalDateTime.buildFromString((rs.getTimestamp("generatedAt")).toString());
		LocalDateTime expiryTime = LocalDateTime.buildFromString((rs.getTimestamp("expiryTime")).toString());
		boolean isExpired = rs.getBoolean("isExpired");
		LocalDateTime creationTime = LocalDateTime.buildFromString((rs.getTimestamp("creationTime")).toString());
		LocalDateTime lastModificationTime = LocalDateTime
				.buildFromString((rs.getTimestamp("lastModificationTime")).toString());

		OneTimePasswordBuilder b = new OneTimePassword.OneTimePasswordBuilder();
		b.userUid(userUid).otp(otp).accepted(accepted).attempts(attempts).generatedAt(generatedAt)
				.expiryTime(expiryTime).isExpired(isExpired);
		b.uid(uid).creationTime(creationTime).lastModificationTime(lastModificationTime).id(id);
		return b.build();
	}

	public OneTimePassword create(String userUid) throws Exception {
		OneTimePasswordBuilder b = new OneTimePassword.OneTimePasswordBuilder();
		String otp = OTPUtility.getOTP(6);
		LocalDateTime expiryTime = new LocalDateTime();
		b.userUid(userUid).otp(otp).accepted(false).attempts(0).generatedAt(new LocalDateTime()).expiryTime(expiryTime)
				.isExpired(false);
		return b.build();
	}

	@Override
	public OneTimePassword createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OneTimePassword> getListFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OneTimePassword createFromMongoDocument(Document doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
