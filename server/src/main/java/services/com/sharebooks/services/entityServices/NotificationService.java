package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.cache.Cache;
import com.sharebooks.dao.generic.NotificationDao;
import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.entities.coreEntities.Notification;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.exception.ExceptionType;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.sources.CacheSource;
import com.sharebooks.sources.DaoSource;

@SuppressWarnings("unchecked")
public class NotificationService extends EntityService {
	// instanceCount varaible will help in replicating the singleton
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());
	private final Cache<Notification> cache = (Cache<Notification>) CacheSource.getCache(EntityType.USER.desc());
	private final NotificationDao dao = (NotificationDao) DaoSource.getDao(EntityType.NOTIFICATION.desc());

	public NotificationService() {
		synchronized (NotificationService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	public List<Notification> getNotifications(String receiverUid) throws Exception {
		List<Notification> notifications = dao.getNotifications(receiverUid);
		return notifications;
	}
}
