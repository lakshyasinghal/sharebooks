package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
//import com.sharebooks.requestProcessor.BookRequestProcessor;


@Path("/test-service")
public class TestResource {
	//private BookRequestProcessor requestProcessor = BookRequestProcessor.getInstance();
	
	@GET
	@Path("/test1")
	@Produces(MediaType.APPLICATION_JSON)
	public String test1(@Context HttpServletRequest req) throws Exception{
		return "{\"options\":[\"GET\" , \"POST\" , \"PUT\" , \"DELETE\"]}";
	}
	
	@GET
	@Path("/test2")
	@Produces(MediaType.TEXT_PLAIN)
	public String test2(@Context HttpServletRequest req) throws Exception{
		return "This service is meant to return plain text only.";
	}
	
//	@GET
//	@Path("/books/{bookId}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String getBookById(@Context HttpServletRequest req , @PathParam("bookId") String id) throws Exception{
//		return requestProcessor.processGetBookByIdRequest(id);
//	}
}
