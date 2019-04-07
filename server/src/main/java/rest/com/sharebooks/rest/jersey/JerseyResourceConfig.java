package com.sharebooks.rest.jersey;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class JerseyResourceConfig extends ResourceConfig {
	//private static final Logger logger = LoggerFactory.getLogger(JerseyResourceConfig.class);

	public JerseyResourceConfig() {
		//logger.info("Configuring ResourceConfig");
		packages("com.sharebooks.rest.resources");
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		//logger.info("Configured ResourceConfig");
	}
}
