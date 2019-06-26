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

import com.sharebooks.requestProcessor.PaymentRequestProcessor;

@Path("/api")
public class PaymentResource {
	private static final Logger LOGGER = Logger.getLogger(PaymentResource.class.getName());
	private PaymentRequestProcessor requestProcessor = PaymentRequestProcessor.getInstance();

	@GET
	@Path("/registration/payment/url/{userUid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRegistrationPaymentURL(@PathParam("userUid") String userUid) throws Exception {
		return requestProcessor.processRegistrationPaymentURLRequest(userUid);
	}

	@POST
	@Path("/payment/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String updatePaymentStatus(@Context HttpServletRequest req) throws Exception {
		return requestProcessor.processUpdatePaymentStatusRequest(req);
	}

//	@PUT
//	@Path("/quotes")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String createQuote(@Context HttpServletRequest req) throws Exception{
//		return requestProcessor.processCreateQuoteRequest(req);
//	}

//	@GET
//	@Path("/quotes/summary/{uid}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String getSummary(@Context HttpServletRequest req, @PathParam("uid") String uid) throws Exception{
//		return requestProcessor.processGetSummaryRequest(uid);
//	}
//	
//	@POST
//	@Path("/quotes/confirm/{uid}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String confirmQuote(@Context HttpServletRequest req, @PathParam("uid") String uid) throws Exception{
//		return requestProcessor.processConfirmQuoteRequest(uid);
//	}
}
