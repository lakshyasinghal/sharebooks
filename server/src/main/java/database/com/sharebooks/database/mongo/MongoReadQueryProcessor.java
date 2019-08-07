package com.sharebooks.database.mongo;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.sharebooks.database.mongo.queries.MongoReadQuery;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;

public class MongoReadQueryProcessor extends AbstractMongoQueryProcessor {
	private static final Logger LOGGER = Logger.getLogger(MongoReadQueryProcessor.class);
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

	/* DBCollection not supported by current mongo driver version */
//	public List<? extends Entity> process(String dbName, String collectionName, MongoReadQuery query,
//			EntityType entityType) throws Exception {
//		LOGGER.trace("Entered process method");
//		MongoResultProcessor resultProcessor = MongoResultProcessor.getInstance();
//		// Getting access to the database
//		MongoDatabase database = getConnection(dbName);
//		DBCollection col = null;
//		// Retrieve the collection
//		try {
//			col = (DBCollection) database.getCollection(collectionName);
//		} catch (NullPointerException ex) {
//			database.createCollection(collectionName);
//			try {
//				col = (DBCollection) database.getCollection(collectionName);
//			} catch (Exception e) {
//				throw e;
//			}
//		} catch (Exception ex) {
//			throw ex;
//		}
//		query.build();
//		DBCursor cursor = col.find(query.query());
//		List<? extends Entity> entityList = resultProcessor.process(cursor, entityType);
//		LOGGER.debug("Document updated successfully");
//		LOGGER.trace("Leaving process method");
//		return entityList;
//	}

	public List<? extends Entity> process(String dbName, String collectionName, MongoReadQuery query,
			EntityType entityType) throws Exception {
		LOGGER.trace("Entered process method");
		MongoResultProcessor resultProcessor = MongoResultProcessor.getInstance();
		// Getting access to the database
		MongoDatabase database = getConnection(dbName);
		MongoCollection<Document> col = null;
		// Retrieve the collection
		try {
			col = database.getCollection(collectionName);
		} catch (NullPointerException ex) {
			database.createCollection(collectionName);
			try {
				col = database.getCollection(collectionName);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception ex) {
			throw ex;
		}
		query.build();
		FindIterable<Document> iterDoc = col.find(query.filter());
		MongoCursor<Document> it = iterDoc.iterator();
		List<Document> documents = new LinkedList<Document>();
		while (it.hasNext()) {
			Document doc = (Document) it.next();
			documents.add(doc);
		}
		List<? extends Entity> entityList = resultProcessor.process(documents, entityType);
		LOGGER.debug("Document updated successfully");
		LOGGER.trace("Leaving process method");
		return entityList;
	}
}
