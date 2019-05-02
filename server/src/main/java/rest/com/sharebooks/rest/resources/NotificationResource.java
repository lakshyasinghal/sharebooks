package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.sharebooks.requestProcessor.NotificationRequestProcessor;

@Path("/api")
public class NotificationResource {
	private static final Logger LOGGER = Logger.getLogger(NotificationResource.class.getName());
	private NotificationRequestProcessor reqProcessor = NotificationRequestProcessor.getInstance();
	
	@GET
	@Path("/notifications/{userUid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNotifications(@Context HttpServletRequest req , @PathParam("userUid") String userUid) throws Exception{
		LOGGER.trace("Entered getNotifications");
		return reqProcessor.processGetNotificationsRequest(userUid);
	}
	
	
//	@POST
//	@Path("/notifications")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String updateNotification(@Context HttpServletRequest req) throws Exception{
//		return reqProcessor.processUpdateNotificationRequest(req);
//	}
}
