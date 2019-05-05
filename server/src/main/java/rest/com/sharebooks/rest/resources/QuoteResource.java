package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.sharebooks.requestProcessor.QuoteRequestProcessor;

@Path("/api")
public class QuoteResource {
	private static final Logger LOGGER = Logger.getLogger(QuoteResource.class.getName());
	private QuoteRequestProcessor requestProcessor = QuoteRequestProcessor.getInstance();
	
	@POST
	@Path("/book-quote")
	@Produces(MediaType.APPLICATION_JSON)
	public String createQuote(@Context HttpServletRequest req, @FormParam("bookUid") String bookUid, @FormParam("userUid") String userUid) throws Exception{
		return requestProcessor.processCreateQuoteRequest(bookUid, userUid);
	}
}
