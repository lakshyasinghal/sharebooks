package com.sharebooks.database.mongo;

import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoDatabase;
import com.sharebooks.database.mongo.queries.MongoReadQuery;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;

public class MongoReadQueryProcessor extends AbstractMongoQueryProcessor {
	private static final Logger LOGGER = Logger.getLogger(MongoUpdateQueryProcessor.class);
	private static MongoReadQueryProcessor instance;

	private MongoReadQueryProcessor() {
	}

	public static MongoReadQueryProcessor instance() {
		if (instance == null) {
			synchronized (MongoReadQueryProcessor.class) {
				if (instance == null) {
					instance = new MongoReadQueryProcessor();
				}
			}
		}
		return instance;
	}

	public List<? extends Entity> process(String dbName, String collectionName, MongoReadQuery query,
			EntityType entityType) throws Exception {
		LOGGER.trace("Entered process method");
		MongoResultProcessor resultProcessor = MongoResultProcessor.getInstance();
		// Getting access to the database
		MongoDatabase database = getConnection(dbName);
		// Retrieve the collection
		DBCollection col = (DBCollection) database.getCollection(collectionName);
		query.build();
		DBCursor cursor = col.find(query.query());
		List<? extends Entity> entityList = resultProcessor.process(cursor, entityType);
		LOGGER.debug("Document updated successfully");
		LOGGER.trace("Leaving process method");
		return entityList;
	}
}
