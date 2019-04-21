package com.sharebooks.jetty;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;

public class CustomErrorHandler extends ErrorPageErrorHandler {
	
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json");
		response.getWriter().append("{\"status\":\"ERROR\",\"message\":\"Do you really think you can hit any api on my website?\"}");
	}
}
