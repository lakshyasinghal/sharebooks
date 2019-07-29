package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;

import com.sharebooks.entities.coreEntities.PasswordResetLink;
import com.sharebooks.entities.coreEntities.PasswordResetLink.PasswordResetLinkBuilder;
import com.sharebooks.util.dateTime.LocalDateTime;

public class PasswordResetLinkFactory implements EntityFactory<PasswordResetLink> {
	private static PasswordResetLinkFactory instance;

	private PasswordResetLinkFactory() {
	}

	public static PasswordResetLinkFactory instance() {
		if (instance == null) {
			synchronized (PasswordResetLinkFactory.class) {
				if (instance == null) {
					instance = new PasswordResetLinkFactory();
				}
			}
		}

		return instance;
	}

	@Override
	public PasswordResetLink createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PasswordResetLink createFromResultSet(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PasswordResetLink createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public PasswordResetLink create(String userUid, String longUrl, String shortUrl) throws Exception {
		PasswordResetLinkBuilder b = new PasswordResetLink.PasswordResetLinkBuilder();
		LocalDateTime expiryTime = LocalDateTime.plusMinutes(30);
		b.userUid(userUid).longUrl(longUrl).shortUrl(shortUrl).expiryTime(expiryTime);
		return b.build();
	}

	@Override
	public List<PasswordResetLink> getListFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PasswordResetLink createFromMongoDocument(Document doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
