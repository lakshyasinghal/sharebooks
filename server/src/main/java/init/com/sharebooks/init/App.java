package com.sharebooks.init;

import org.apache.log4j.Logger;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.ServerProperties;
import com.sharebooks.jetty.GenericServer;
import com.sharebooks.jetty.JettyServer;
import com.sharebooks.sources.CacheSource;
import com.sharebooks.sources.ConnectionPoolSource;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
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
			CacheSource.init();
			ServiceSource.init();
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
		initialize();
		startServer();
		LOGGER.info("Application launched");
	}

//	private static void testMailService() {
//		MailService.instance().sendErrorMail(new Exception("Your app is fucked."));
//	}
//
//	private static void testGeocodingService() throws Exception {
//		Address address = new Address.AddressBuilder().line1("74 B").line2("Sec 11").city("Faridabad").state("Haryana")
//				.build();
//		GeoCoordinates coordinates = GeocodingService.instance().getCoordinatesFromAddress(address);
//		System.out.println(coordinates.lat());
//		System.out.println(coordinates.lng());
//	}

//	private static void testSmsService() throws Exception {
//		String message = "How are you, sucker?";
//		String numbers = "8448054935";
//
//		boolean success = SmsService.instnace().sendSms(message, numbers);
//		System.out.println("Success in sending sms =>" + success);
//	}

//	private static void testUrlShortenerService() throws Exception {
//		String longUrl = "http://www.google.com/fuck-you";
//
//		String shortUrl = UrlShortenerService.instance().getShortUrl(longUrl);
//		System.out.println("Short url =>" + shortUrl);
//	}
}
