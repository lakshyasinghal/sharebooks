package com.sharebooks.init;

import java.util.Map;

import org.apache.log4j.Logger;
import com.sharebooks.jetty.GenericServer;
import com.sharebooks.jetty.JettyServer;
import com.sharebooks.sources.CacheSource;
import com.sharebooks.sources.ConnectionPoolSource;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.PropertySource;
import com.sharebooks.sources.ServiceSource;
import com.sharebooks.test.util.DummyPropertySource;


public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	
	
	public static void main(String[] args) throws Exception {
		launch();
	}
	
	private static void initialize(){
		try{
			Map<String,String> propMap = DummyPropertySource.getPropertyMap();
			PropertySource.init(propMap);
			//LoggerConfigurator.configure();
			FactorySource.init();
			ConnectionPoolSource.init();
			DaoSource.init();
			CacheSource.init();
			ServiceSource.init();
		}
		catch(Exception ex){
			LOGGER.error("Error in starting application",ex);
		}
	}
	
	
	private static void launch() throws Exception {
		LOGGER.info("Launching...");
		initialize();
		int port = Integer.parseInt(PropertySource.getServerPrperty("SERVER_PORT"));
		int idleTimeout = Integer.parseInt(PropertySource.getServerPrperty("SERVER_IDLE_TIMEOUT"));
		GenericServer server = new JettyServer(port,idleTimeout);
		server.start();
		LOGGER.info("Application launched");
	}
}
