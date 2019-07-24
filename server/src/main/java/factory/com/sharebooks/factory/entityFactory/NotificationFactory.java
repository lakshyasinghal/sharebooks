package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.DBObject;
import com.sharebooks.entities.coreEntities.Notification;
import com.sharebooks.entities.coreEntities.enums.NotificationStatus;
import com.sharebooks.entities.coreEntities.enums.NotificationType;
import com.sharebooks.util.dateTime.LocalDateTime;

public class NotificationFactory implements EntityFactory<Notification> {
	private static NotificationFactory instance;

	private NotificationFactory() {
		// TODO Auto-generated constructor stub
	}

	public static NotificationFactory getInstance() throws Exception {
		try {
			if (instance == null) {
				synchronized (NotificationFactory.class) {
					if (instance == null) {
						instance = new NotificationFactory();
					}
				}
			}
			return instance;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Notification createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification createFromResultSet(ResultSet rs) throws Exception {
		int id = rs.getInt("id");
		String uid = rs.getString("uid");
		String receiverUid = rs.getString("receiverUid");
		NotificationType type = NotificationType.valueOf(rs.getInt("type"));
		String bookRequestUid = rs.getString("bookRequestUid");
		String newBookUid = rs.getString("newBookUid");
		NotificationStatus status = NotificationStatus.valueOf(rs.getInt("status"));
		String creationTimeStr = (rs.getTimestamp("creationTime")).toString();
		LocalDateTime creationTime = LocalDateTime.buildFromString(creationTimeStr);
		String lastModificationTimeStr = (rs.getTimestamp("lastModificationTime")).toString();
		LocalDateTime lastModificationTime = LocalDateTime.buildFromString(lastModificationTimeStr);

		Notification notification = new Notification(id, uid, receiverUid, type, bookRequestUid, newBookUid, status,
				creationTime, lastModificationTime);
		return notification;
	}

	@Override
	public Notification createFromJson(String json) throws Exception {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject) obj;

			int id = jo.get("id") == null ? -1 : (int) (long) jo.get("id");
			String uid = (String) jo.get("uid");
			String receiverUid = (String) jo.get("receiverUid");
			NotificationType type = NotificationType.valueOf((int) (long) jo.get("type"));
			String bookRequestUid = (String) jo.get("bookRequestUid");
			String newBookUid = (String) jo.get("newBookUid");
			NotificationStatus status = NotificationStatus.valueOf((int) (long) jo.get("status"));

			String creationTimeStr = (String) jo.get("creationTime");
			String lastModificationTimeStr = (String) jo.get("lastModificationTime");

			LocalDateTime creationTime = creationTimeStr == null ? null
					: LocalDateTime.buildFromString(creationTimeStr);
			LocalDateTime lastModificationTime = lastModificationTimeStr == null ? null
					: LocalDateTime.buildFromString(lastModificationTimeStr);

			Notification notification = new Notification(id, uid, receiverUid, type, bookRequestUid, newBookUid, status,
					creationTime, lastModificationTime);
			return notification;
		} catch (ParseException ex) {
			throw ex;
		}
	}

	public Notification create(String receiverUid, NotificationType type, String bookRequestUid, String newBookUid) {
		return new Notification(-1, UUID.randomUUID().toString(), receiverUid, type, bookRequestUid, newBookUid,
				NotificationStatus.NEW, null, null);
	}

	@Override
	public List<Notification> getListFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification createFromMongoDatabaseObject(DBObject dbObj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
