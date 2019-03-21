package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/user-service")
public class UserResource {
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUsers(){
		return "[{name:\"Lakshya\"} , {name:\"Himanshu\"} , {name:\"Anil\"}]";
	}
	
	@GET
	@Path("/users/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserById(@Context HttpServletRequest req , @PathParam("userId") String userId){
		return "{name:\"Lakshya Singhal\", age:28}";
	}
	
	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(@Context HttpServletRequest req){
		return "{success:\"true\"}";
	}
	
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@Context HttpServletRequest req){
		return "{success:\"true\"}";
	}
	
	@DELETE
	@Path("/users/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(){
		return "{success:\"false\"}";
	}
	
	@OPTIONS
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOptions(){
		return "<operations>GET , PUT , POST , DELETE</operations>";
	}
}
