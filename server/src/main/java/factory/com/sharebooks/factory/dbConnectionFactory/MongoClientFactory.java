package com.sharebooks.factory.dbConnectionFactory;

import com.mongodb.MongoClient;

public class MongoClientFactory {
	private static MongoClientFactory instance;

	private MongoClientFactory() {
	}

	public static MongoClientFactory instance() {
		if (instance == null) {
			synchronized (MongoClientFactory.class) {
				if (instance == null) {
					instance = new MongoClientFactory();
				}
			}
		}

		return instance;
	}

	public MongoClient getMongoClient(String host, int port, String dbName, String username, String password,
			int capacity) throws Exception {
		MongoClient client = new MongoClient(host, port);
		return client;
	}

}
