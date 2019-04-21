package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.sharebooks.requestProcessor.BookRequestsRequestProcessor;


@Path("/api")
public class BookRequestResource {
	private static final Logger LOGGER = Logger.getLogger(BookRequestResource.class.getName());
	private BookRequestsRequestProcessor requestProcessor = BookRequestsRequestProcessor.getInstance();
	
	
	@GET
	@Path("/book-requests/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBookRequestsByOwnerUid(@PathParam("uid") String uid) throws Exception{
		return requestProcessor.processGetBookRequestsByUid(uid);
	}
	
	@PUT
	@Path("/book-requests")
	@Produces(MediaType.APPLICATION_JSON)
	public String createBookRequest(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processCreateBookRequest(req);
	}
	
	@POST
	@Path("/book-requests")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBookRequest(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processUpdateBookRequest(req);
	}
	
	@POST
	@Path("/book-requests/accept")
	@Produces(MediaType.APPLICATION_JSON)
	public String acceptBookRequest(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processAcceptBookRequest(req);
	}
	
	@POST
	@Path("/book-requests/reject")
	@Produces(MediaType.APPLICATION_JSON)
	public String rejectBookRequest(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processRejectBookRequest(req);
	}
}
