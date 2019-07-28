package com.sharebooks.database.mongo;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.sharebooks.database.mongo.queries.MongoDeleteQuery;

public class MongoDeleteQueryProcessor extends AbstractMongoQueryProcessor {
	private static final Logger LOGGER = Logger.getLogger(MongoDeleteQueryProcessor.class);
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
		MongoCollection<Document> col = database.getCollection(collectionName);
		query.build();
		DeleteResult result = col.deleteOne(query.filter());
		LOGGER.debug("Document inserted successfully");
		LOGGER.trace("Leaving insert method");
		return (int) result.getDeletedCount();
	}
}
