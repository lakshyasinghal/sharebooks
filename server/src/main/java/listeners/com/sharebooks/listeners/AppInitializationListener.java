package com.sharebooks.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sharebooks.init.AppInitializer;

public class AppInitializationListener implements ServletContextListener{


	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext ctxt = event.getServletContext();
		AppInitializer initializer = new AppInitializer();
		initializer.initialize(ctxt);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}
}
