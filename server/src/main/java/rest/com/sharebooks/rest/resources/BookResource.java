package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.sharebooks.requestProcessor.BookRequestProcessor;


@Path("/api")
public class BookResource {
	private static final Logger LOGGER = Logger.getLogger(BookResource.class.getName());
	private BookRequestProcessor requestProcessor = BookRequestProcessor.getInstance();
	
	@GET
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllBooks(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processGetAllBooksRequest();
	}
	
	@GET
	@Path("/books/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBookById(@Context HttpServletRequest req , @PathParam("uid") String uid) throws Exception{
		return requestProcessor.processGetBookRequest(uid);
	}
	
	@GET
	@Path("/books/search/{searchTerm}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBooksBySearchTerm(@Context HttpServletRequest req , @PathParam("searchTerm") String searchTerm) throws Exception{
		return requestProcessor.processGetBooksBySearchTermRequest(searchTerm);
	}
	
	@GET
	@Path("/books/category/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBooksByCategory(@Context HttpServletRequest req , @PathParam("category") String category) throws Exception{
		return requestProcessor.processGetBooksByCategoryRequest(category);
	}
	
	@PUT
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String createBook(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processCreateRequest(req);
	}
	
	@POST
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBook(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processUpdateRequest(req);
	}
	
	@POST
	@Path("/books/{uid}/image")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBook(@Context HttpServletRequest req, @PathParam("uid") String uid, @FormParam("imgSrc") String imgSrc) throws Exception{
		return requestProcessor.processUploadImageSrcRequest(uid,imgSrc);
	}
	
	@DELETE
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteBooks(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processDeleteBooksRequest(req);
	}
	
	@DELETE
	@Path("/books/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteBookByUid(@Context HttpServletRequest req , @PathParam("uid") String uid) throws Exception{
		return requestProcessor.processDeleteBookRequest(uid);
	}
	
	@OPTIONS
	@Path("/books")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSupportedOptions() throws Exception{
		return "{\"options\":[\"GET\" , \"POST\" , \"PUT\" , \"DELETE\"]}";
	}
}
