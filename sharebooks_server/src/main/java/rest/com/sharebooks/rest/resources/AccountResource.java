package com.sharebooks.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/accountservice")
public class AccountResource {
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@Context HttpServletRequest req , @FormParam("username") String username , @FormParam("password") String password){
		return "";
	}
	
	
	
}
