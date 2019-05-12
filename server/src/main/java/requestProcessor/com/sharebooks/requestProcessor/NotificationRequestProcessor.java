package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.sharebooks.entities.coreEntities.Notification;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.services.entityServices.NotificationService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;


public class NotificationRequestProcessor extends AbstractRequestProcessor{
	private static NotificationRequestProcessor instance = new NotificationRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(NotificationRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final NotificationService notificationService = ServiceSource.getNotificationService();
	//private final EntityFactory<Notification> factory = (EntityFactory<Notification>) FactorySource.getEntityFactory(EntityType.NOTIFICATION.desc());
	
	
	private NotificationRequestProcessor(){
		
	}
	
	public static NotificationRequestProcessor getInstance(){
		return instance;
	}
	
	
	//the notifications will be fetched for a particular user using the user uid which will be the recepient uid in notifications
	public String processGetNotificationsRequest(String receiverUid) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		List<Notification> notifications = null;
		boolean success = false;
		int statusCode = -1,errorCode = -1;
		try{
			notifications = notificationService.getNotifications(receiverUid);
			if(notifications==null || notifications.size()==0){
				statusCode = Status.NO_NOTIFICATIONS.id();
			}
			else{
				statusCode = Status.FETCH_NOTIFICATIONS_SUCCESSFUL.id();
			}
			success = true;
			map.put("notifications", notifications);
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		return response.process();
	}
}
