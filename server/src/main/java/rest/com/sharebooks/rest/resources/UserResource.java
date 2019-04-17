package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sharebooks.requestProcessor.UserRequestProcessor;

@Path("/api")
public class UserResource {
	private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());
	private UserRequestProcessor reqProcessor = UserRequestProcessor.getInstance();
	
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
		return reqProcessor.processGetAllRequest();
	}
	
	@GET
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserById(@Context HttpServletRequest req , @PathParam("id") String id) throws Exception{
		return reqProcessor.processGetByIdRequest(id);
	}
	
	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(@Context HttpServletRequest req) throws Exception{
		return reqProcessor.processCreateRequest(req);
	}
	
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@Context HttpServletRequest req) throws Exception{
		return reqProcessor.processUpdateRequest(req);
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
