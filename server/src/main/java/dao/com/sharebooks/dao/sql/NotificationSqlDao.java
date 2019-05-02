package com.sharebooks.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.sharebooks.coreEntities.Notification;
import com.sharebooks.coreEntities.User;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.AbstractNotificationDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.entity.Entity;
import com.sharebooks.factory.entityFactory.EntityFactory;


public class NotificationSqlDao extends AbstractNotificationDao {
	private static final Logger LOGGER = Logger.getLogger(NotificationSqlDao.class.getName());
	//private EntityFactory<User> factory;
	private final Database database = Database.SHAREBOOKS;
	private final Table table = Table.NOTIFICATIONS;

	
	@Override
	public List<Notification> getAllNotifications() {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNotifications(String receiverUid) throws Exception {
		LOGGER.trace("Entered getNotifications");
		List<Notification> notifications = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("receiverUid", receiverUid);
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		//get sql read query
		SqlQuery query = new SqlReadQuery(table.desc() , map);
		query.build();
		LOGGER.info(query.toString());
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.NOTIFICATION);
		notifications = convertIntoNotificationList(entityList);
		LOGGER.trace("Leaving getNotifications");
		return notifications;
	}

	@Override
	public boolean updateNotification(Notification notification) {
		// TODO Auto-generated method stub
		return false;
	}


}
