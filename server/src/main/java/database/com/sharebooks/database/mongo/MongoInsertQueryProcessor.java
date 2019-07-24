package com.sharebooks.database.mongo;

import org.apache.log4j.Logger;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoDatabase;
import com.sharebooks.database.mongo.queries.MongoInsertQuery;

public class MongoInsertQueryProcessor extends AbstractMongoQueryProcessor {
	private static final Logger LOGGER = Logger.getLogger(MongoInsertQueryProcessor.class);
	private static MongoInsertQueryProcessor instance;

	private MongoInsertQueryProcessor() {
	}

	public static MongoInsertQueryProcessor instance() {
		if (instance == null) {
			synchronized (MongoInsertQueryProcessor.class) {
				if (instance == null) {
					instance = new MongoInsertQueryProcessor();
				}
			}
		}
		return instance;
	}

	public int process(String dbName, String collectionName, MongoInsertQuery query) throws Exception {
		LOGGER.trace("Entered insert method");
		// Getting access to the database
		MongoDatabase database = getConnection(dbName);
		// Retrieve the collection
		DBCollection col = (DBCollection) database.getCollection(collectionName);
		query.build();
		WriteResult result = col.insert(query.query());
		LOGGER.debug("Document inserted successfully");
		LOGGER.trace("Leaving insert method");
		return result.getN();
	}
}
