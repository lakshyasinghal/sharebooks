package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sharebooks.requestProcessor.BookRequestProcessor;
import com.sharebooks.requestProcessor.ResultRequestProcessor;

@Path("/api")
public class ResultResource {
	private static final Logger LOGGER = Logger.getLogger(ResultResource.class.getName());
	private ResultRequestProcessor requestProcessor = ResultRequestProcessor.getInstance();
	
	@GET
	@Path("/results/{bookUid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getResult(@Context HttpServletRequest req, @PathParam("bookUid") String bookUid) throws Exception{
		return requestProcessor.processGetResultRequest(bookUid);
	}
	
	@POST
	@Path("/results")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSimilarResults(@Context HttpServletRequest req , @PathParam("bookUid") String bookUid) throws Exception{
		return requestProcessor.processGetSimilarResultsRequest(req);
	}
}
