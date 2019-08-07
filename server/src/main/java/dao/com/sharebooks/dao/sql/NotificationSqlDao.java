package com.sharebooks.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.NotificationDao;
import com.sharebooks.dao.util.EntityConverterUtility;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.entities.coreEntities.Notification;
import com.sharebooks.entities.coreEntities.enums.EntityType;

public class NotificationSqlDao extends AbstractSqlDao implements NotificationDao {
	private static final Logger LOGGER = Logger.getLogger(NotificationSqlDao.class.getName());
	// private EntityFactory<User> factory;
	private final Database database = Database.CORE;
	private final Table table = Table.NOTIFICATIONS;

	@Override
	public List<Notification> getAllNotifications() {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNotifications(String receiverUid) throws Exception {
		LOGGER.trace("Entered getNotifications");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receiverUid", receiverUid);
		return EntityConverterUtility
				.convertIntoNotificationList(super.get(map, database, table, EntityType.NOTIFICATION));
	}

	@Override
	public boolean updateNotification(Notification notification) {
		// TODO Auto-generated method stub
		return false;
	}

}
