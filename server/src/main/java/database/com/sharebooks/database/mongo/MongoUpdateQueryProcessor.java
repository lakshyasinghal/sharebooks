package com.sharebooks.database.mongo;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.sharebooks.database.mongo.queries.MongoUpdateQuery;

public class MongoUpdateQueryProcessor extends AbstractMongoQueryProcessor {
	private static final Logger LOGGER = Logger.getLogger(MongoUpdateQueryProcessor.class);
	private static MongoUpdateQueryProcessor instance;

	private MongoUpdateQueryProcessor() {
	}

	public static MongoUpdateQueryProcessor instance() {
		if (instance == null) {
			synchronized (MongoUpdateQueryProcessor.class) {
				if (instance == null) {
					instance = new MongoUpdateQueryProcessor();
				}
			}
		}
		return instance;
	}

//	public int process(String dbName, String collectionName, MongoUpdateQuery query) throws Exception {
//		LOGGER.trace("Entered process method");
//		// Getting access to the database
//		MongoDatabase database = getConnection(dbName);
//		// Retrieve the collection
//		DBCollection col = (DBCollection) database.getCollection(collectionName);
//		query.build();
//		WriteResult result = col.update(query.queryObj(), query.updateObj());
//		LOGGER.debug("Document updated successfully");
//		LOGGER.trace("Leaving process method");
//		return result.getN();
//	}

	public int process(String dbName, String collectionName, MongoUpdateQuery query) throws Exception {
		LOGGER.trace("Entered process method");
		// Getting access to the database
		MongoDatabase database = getConnection(dbName);
		// Retrieve the collection
		MongoCollection<Document> col = database.getCollection(collectionName);
		query.build();
		UpdateResult result = col.updateOne(query.filter(), query.updateDoc());
		// WriteResult result = col.update(query.queryObj(), query.updateObj());
		LOGGER.debug("Document updated successfully");
		LOGGER.trace("Leaving process method");
		return (int) result.getModifiedCount();
	}
}
