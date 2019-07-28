package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;

import com.sharebooks.entities.coreEntities.Subscription;
import com.sharebooks.entities.coreEntities.Subscription.SubscriptionBuilder;
import com.sharebooks.entities.coreEntities.enums.Active;
import com.sharebooks.entities.coreEntities.enums.SubscriptionType;
import com.sharebooks.util.dateTime.LocalDateTime;

public class SubscriptionFactory implements EntityFactory<Subscription> {
	private static SubscriptionFactory instance;

	private SubscriptionFactory() {

	}

	public static SubscriptionFactory instance() {
		if (instance == null) {
			synchronized (SubscriptionFactory.class) {
				if (instance == null) {
					instance = new SubscriptionFactory();
				}
			}
		}
		return instance;
	}

	@Override
	public Subscription createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscription createFromResultSet(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscription createFromMongoDocument(Document doc) throws Exception {
		String uid = doc.getString("uid");
		String userUid = doc.getString("userUid");
		SubscriptionType type = SubscriptionType.get(doc.getInteger("type"));
		Active active = Active.valueOf(doc.getInteger("active"));
		LocalDateTime startDateTime = LocalDateTime.buildFromString(doc.getString("startDateTime"));
		LocalDateTime endDateTime = LocalDateTime.buildFromString(doc.getString("endDateTime"));
		LocalDateTime creationTime = LocalDateTime.buildFromString(doc.getString("creationTime"));
		LocalDateTime lastModificationTime = LocalDateTime.buildFromString(doc.getString("lastModificationTime"));

		SubscriptionBuilder b = new Subscription.SubscriptionBuilder();
		b.active(active).userUid(userUid).startDateTime(startDateTime).endDateTime(endDateTime).type(type).uid(uid)
				.creationTime(creationTime).lastModificationTime(lastModificationTime);
		return b.build();
	}

	@Override
	public Subscription createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subscription> getListFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
