package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sharebooks.requestProcessor.UsersRequestProcessor;

@Path("/api")
public class UserResource {
	private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());
	private UsersRequestProcessor reqProcessor = UsersRequestProcessor.getInstance();
	
	@POST
	@Path("/users/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@Context HttpServletRequest req , @FormParam("username") String username, @FormParam("password") String password) throws Exception{
		return reqProcessor.processLoginRequest(username,password);
	}
	
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUsers(@Context HttpServletRequest req) throws Exception{
		return reqProcessor.processGetAllUsersRequest();
	}
	
	@GET
	@Path("/users/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@Context HttpServletRequest req , @PathParam("uid") String uid) throws Exception{
		return reqProcessor.processGetUserRequest(uid);
	}
	
	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(@Context HttpServletRequest req) throws Exception{
		return reqProcessor.processCreateUserRequest(req);
	}
	
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@Context HttpServletRequest req) throws Exception{
		return reqProcessor.processUpdateUserRequest(req);
	}
	
	@DELETE
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@Context HttpServletRequest req , @PathParam("id") String id){
		return "{success:\"false\"}";
	}
	
	@OPTIONS
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOptions(){
		return "<operations>GET , PUT , POST , DELETE</operations>";
	}
}
