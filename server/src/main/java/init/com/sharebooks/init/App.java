package com.sharebooks.init;

import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.appConfig.AppConfig;
import com.sharebooks.jetty.GenericServer;
import com.sharebooks.jetty.JettyServer;
import com.sharebooks.sources.CacheSource;
import com.sharebooks.sources.ConnectionPoolSource;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;
import com.sharebooks.test.util.DummyPropertySource;

public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	private static String configFolderPath = "";

	public static void main(String[] args) throws Exception {
		configFolderPath = args[0];
		launch();
	}

	private static void initialize() {
		try {
			Map<String, String> propMap = DummyPropertySource.getPropertyMap();
			AppConfig.init(configFolderPath);
			// LoggerConfigurator.configure();
			FactorySource.init();
			ConnectionPoolSource.init();
			DaoSource.init();
			CacheSource.init();
			ServiceSource.init();
		} catch (Exception ex) {
			LOGGER.error("Error in starting application", ex);
		}
	}

	private static void startServer() throws Exception {
		int port = Integer.parseInt(AppConfig.getServerProperty("SERVER_PORT"));
		int idleTimeout = Integer.parseInt(AppConfig.getServerProperty("SERVER_IDLE_TIMEOUT"));
		GenericServer server = new JettyServer(port, idleTimeout);
		server.start();
	}

	private static void launch() throws Exception {
		LOGGER.info("Launching...");
		initialize();
		startServer();
		LOGGER.info("Application launched");
	}
}
