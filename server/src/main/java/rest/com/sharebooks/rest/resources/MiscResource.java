package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.sharebooks.requestProcessor.BookCategoryRequestProcessor;
import com.sharebooks.requestProcessor.CityRequestProcessor;
import com.sharebooks.requestProcessor.StateRequestProcessor;


@Path("/api")
public class MiscResource {
	private static final Logger LOGGER = Logger.getLogger(MiscResource.class.getName());
	private StateRequestProcessor stateRequestProcessor = StateRequestProcessor.getInstance();
	private CityRequestProcessor cityRequestProcessor = CityRequestProcessor.getInstance();
	private BookCategoryRequestProcessor bookCategoryRequestProcessor = BookCategoryRequestProcessor.getInstance();
	
	@GET
	@Path("/book-categories")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBookCategories() throws Exception{
		return bookCategoryRequestProcessor.processGetBookCategoriesRequest();
	}
	
	@GET
	@Path("/states")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStates(@Context HttpServletRequest req , @PathParam("id") String id) throws Exception{
		return stateRequestProcessor.processGetStatesRequest();
	}
	
	@GET
	@Path("/cities")
	@Produces(MediaType.APPLICATION_JSON)
	public String createBook(@Context HttpServletRequest req) throws Exception{
		return cityRequestProcessor.processGetCitiesRequest();
	}
}





