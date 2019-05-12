package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.sharebooks.requestProcessor.QuoteRequestProcessor;

@Path("/api")
public class QuoteResource {
	private static final Logger LOGGER = Logger.getLogger(QuoteResource.class.getName());
	private QuoteRequestProcessor requestProcessor = QuoteRequestProcessor.getInstance();
	
	@GET
	@Path("/quotes/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuote(@Context HttpServletRequest req, @PathParam("uid") String uid) throws Exception{
		return requestProcessor.processGetQuoteRequest(uid);
	}
	
	@PUT
	@Path("/quotes")
	@Produces(MediaType.APPLICATION_JSON)
	public String createQuote(@Context HttpServletRequest req) throws Exception{
		return requestProcessor.processCreateQuoteRequest(req);
	}
	
	@POST
	@Path("/quotes/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateQuote(@Context HttpServletRequest req, @PathParam("uid") String uid) throws Exception{
		return requestProcessor.processCreateQuoteRequest(req);
	}
	
	@GET
	@Path("/quotes/summary/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSummary(@Context HttpServletRequest req, @PathParam("uid") String uid) throws Exception{
		return requestProcessor.processGetSummaryRequest(uid);
	}
	
	@POST
	@Path("/quotes/confirm/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String confirmQuote(@Context HttpServletRequest req, @PathParam("uid") String uid) throws Exception{
		return requestProcessor.processConfirmQuoteRequest(uid);
	}
}
