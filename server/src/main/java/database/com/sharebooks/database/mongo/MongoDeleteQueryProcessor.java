package com.sharebooks.database.mongo;

import org.apache.log4j.Logger;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoDatabase;
import com.sharebooks.database.mongo.queries.MongoDeleteQuery;

public class MongoDeleteQueryProcessor extends AbstractMongoQueryProcessor {

	private static final Logger LOGGER = Logger.getLogger(MongoInsertQueryProcessor.class);
	private static MongoDeleteQueryProcessor instance;

	private MongoDeleteQueryProcessor() {
	}

	public static MongoDeleteQueryProcessor instance() {
		if (instance == null) {
			synchronized (MongoDeleteQueryProcessor.class) {
				if (instance == null) {
					instance = new MongoDeleteQueryProcessor();
				}
			}
		}
		return instance;
	}

	public int process(String dbName, String collectionName, MongoDeleteQuery query) throws Exception {
		LOGGER.trace("Entered insert method");
		// Getting access to the database
		MongoDatabase database = getConnection(dbName);
		// Retrieve the collection
		DBCollection col = (DBCollection) database.getCollection(collectionName);
		query.build();
		WriteResult result = col.remove(query.query());
		LOGGER.debug("Document inserted successfully");
		LOGGER.trace("Leaving insert method");
		return result.getN();
	}
}
