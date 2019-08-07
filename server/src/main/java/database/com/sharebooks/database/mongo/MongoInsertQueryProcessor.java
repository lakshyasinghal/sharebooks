package com.sharebooks.database.mongo;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
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
		MongoCollection<Document> col = database.getCollection(collectionName);
		query.build();
		col.insertOne(query.query());
		LOGGER.debug("Document inserted successfully");
		LOGGER.trace("Leaving insert method");
		return 1;
	}
}
