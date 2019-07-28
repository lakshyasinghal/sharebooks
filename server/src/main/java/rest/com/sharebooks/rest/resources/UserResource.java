package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sharebooks.requestProcessor.UserRequestProcessor;

@Path("/api")
public class UserResource {
	private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());
	private UserRequestProcessor reqProcessor = UserRequestProcessor.getInstance();

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@Context HttpServletRequest req, @FormParam("username") String username,
			@FormParam("password") String password) throws Exception {
		return reqProcessor.processLoginRequest(username, password);
	}

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUsers(@Context HttpServletRequest req) throws Exception {
		return reqProcessor.processGetAllUsersRequest();
	}

	@GET
	@Path("/users/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@Context HttpServletRequest req, @PathParam("uid") String uid) throws Exception {
		return reqProcessor.processGetUserRequest(uid);
	}

	// input type - json
	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(@Context HttpServletRequest req) throws Exception {
		return reqProcessor.processCreateUserRequest(req);
	}

	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@Context HttpServletRequest req) throws Exception {
		return reqProcessor.processUpdateUserRequest(req);
	}

	@DELETE
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@Context HttpServletRequest req, @PathParam("id") String id) {
		return "{success:\"false\"}";
	}

	@POST
	@Path("/users/{uid}/preferences")
	@Produces(MediaType.APPLICATION_JSON)
	public String savePreferences(@PathParam("uid") String uid, @Context HttpServletRequest req) throws Exception {
		return reqProcessor.processSavePreferencesRequest(uid, req);
	}

	@POST
	@Path("/users/{uid}/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateProfile(@PathParam("uid") String uid, @FormParam("name") String name,
			@FormParam("username") String username, @FormParam("contactNo") String contactNo,
			@FormParam("password") String password) throws Exception {
		return reqProcessor.processUpdateProfileRequest(uid, name, username, contactNo, password);
	}

	@POST
	@Path("/users/{uid}/otp/send")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendOTP(@PathParam("uid") String userUid, @FormParam("contactNo") String contactNo) throws Exception {
		return reqProcessor.processSendOTP(userUid, contactNo);
	}

	@POST
	@Path("/users/{uid}/otp/verify")
	@Produces(MediaType.APPLICATION_JSON)
	public String verifyOTP(@PathParam("uid") String userUid, @FormParam("otp") String otp) throws Exception {
		return reqProcessor.processSendOTP(userUid, otp);
	}

	@POST
	@Path("/users/{uid}/password")
	@Produces(MediaType.APPLICATION_JSON)
	public String resetPassword(@PathParam("uid") String userUid, @FormParam("linkUid") String linkUid,
			@FormParam("password") String password) throws Exception {
		return reqProcessor.processResetPassword(linkUid, userUid, password);
	}

	@OPTIONS
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOptions() {
		return "<operations>GET , PUT , POST , DELETE</operations>";
	}
}
