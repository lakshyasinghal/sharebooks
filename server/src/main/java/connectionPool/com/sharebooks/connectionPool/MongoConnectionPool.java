package com.sharebooks.connectionPool;

import org.apache.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.sharebooks.exception.NonFunctionalMethodException;
import com.sharebooks.factory.dbConnectionFactory.MongoClientFactory;

//This class will be used as a wrapper for MongoClient which itself is a mongo connection pool container
public class MongoConnectionPool implements DatabaseConnectionPool {
	private static Logger LOGGER = Logger.getLogger(MongoConnectionPool.class);
	private MongoClient mongoClient;
	private String dbName;

	public MongoConnectionPool(MongoConnectionPoolBuilder b) throws Exception {
		dbName = b.name;
		mongoClient = MongoClientFactory.instance().getMongoClient(b.host, b.port, b.name, b.username, b.password,
				b.capacity);
	}

	public static class MongoConnectionPoolBuilder {
		private String host;
		private int port;
		private String name; // database name
		private String username;
		private String password;
		private int capacity;

		public MongoConnectionPoolBuilder host(String host) {
			this.host = host;
			return this;
		}

		public MongoConnectionPoolBuilder port(int port) {
			this.port = port;
			return this;
		}

		public MongoConnectionPoolBuilder name(String name) {
			this.name = name;
			return this;
		}

		public MongoConnectionPoolBuilder username(String username) {
			this.username = username;
			return this;
		}

		public MongoConnectionPoolBuilder password(String password) {
			this.password = password;
			return this;
		}

		public MongoConnectionPoolBuilder capacity(int capacity) {
			this.capacity = capacity;
			return this;
		}

		public MongoConnectionPool build() throws Exception {
			return new MongoConnectionPool(this);
		}
	}

	@Override
	public void init(int capacity) throws Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public MongoDatabase getConnection() throws Exception {
		LOGGER.debug("Getting mongo connection to database " + dbName);
		return mongoClient.getDatabase(dbName);
	}

	@Override
	public void releaseConnection(Object obj) throws Exception {
		// do nothing
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return 0;
	}
}
