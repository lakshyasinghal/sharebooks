package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sharebooks.coreEntities.BookRequest;
import com.sharebooks.requestProcessor.BookRequestProcessor;


@Path("/api")
public class BookResource {
	private static final Logger LOGGER = Logger.getLogger(BookRequest.class.getName());
	private BookRequestProcessor requestProcessor = BookRequestProcessor.getInstance();
	
	@GET
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllBooks(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processGetAllBooksRequest();
	}
	
	@GET
	@Path("/books/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBookById(@Context HttpServletRequest req , @PathParam("id") String id) throws Exception{
		return requestProcessor.processGetBookByIdRequest(id);
	}
	
	@PUT
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertBook(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processCreateRequest(req);
	}
	
	@POST
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBook(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processUpdateRequest(req);
	}
	
	@POST
	@Path("/books/{id}/image")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBook(@Context HttpServletRequest req, @PathParam("id") String id, @FormParam("imgSrc") String imgSrc) throws Exception{
		return requestProcessor.processUploadImageSrcRequest(id,imgSrc);
	}
	
	
	@DELETE
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteBooks(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processDeleteBooksRequest(req);
	}
	
	@DELETE
	@Path("/books/{bookId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteBookById(@Context HttpServletRequest req , @PathParam("bookId") String id) throws Exception{
		return requestProcessor.processDeleteBookByIdRequest(id);
	}
	
	@OPTIONS
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSupportedOptions() throws Exception{
		return "{\"options\":[\"GET\" , \"POST\" , \"PUT\" , \"DELETE\"]}";
	}
}
