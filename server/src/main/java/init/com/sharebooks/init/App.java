package com.sharebooks.init;

import org.apache.log4j.Logger;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.ServerProperties;
import com.sharebooks.jetty.GenericServer;
import com.sharebooks.jetty.JettyServer;
import com.sharebooks.sources.ConnectionPoolSource;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	private static AppConfigurator configurator = null;
	private static String configFolderPath = "";

	public static void main(String[] args) throws Exception {
		configFolderPath = args[0];
		launch();
	}

	private static void initialize() {
		try {
			// Map<String, String> propMap = DummyPropertySource.getPropertyMap();
			AppConfig.init(configFolderPath);
			// LoggerConfigurator.configure();
			FactorySource.init();
			ConnectionPoolSource.init();
			DaoSource.init();
			// CacheSource.init();
			ServiceSource.init();

			configurator.configureCache();
		} catch (Exception ex) {
			LOGGER.error("Error in starting application", ex);
		}
	}

	private static void startServer() throws Exception {
		int port = Integer.parseInt(AppConfig.serverProp(ServerProperties.SERVER_PORT));
		int idleTimeout = Integer.parseInt(AppConfig.serverProp(ServerProperties.SERVER_IDLE_TIMEOUT));
		GenericServer server = new JettyServer(port, idleTimeout);
		server.start();
		// testMailService();
		// testGeocodingService();
		// testSmsService();
		// testUrlShortenerService();
	}

	private static void launch() throws Exception {
		LOGGER.info("Launching...");
		configurator = AppConfigurator.instance();
		initialize();
		startServer();
		LOGGER.info("Application launched");
	}
}
