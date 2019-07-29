package com.sharebooks.database.mongo.queries;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.bson.Document;

public class MongoInsertQuery {
	private static final Logger LOGGER = Logger.getLogger(MongoInsertQuery.class.getName());
	private final Map<String, Object> insertObjMap;
	private Document document;
	// private DBObject insertDbObject;

	public MongoInsertQuery(Map<String, Object> insertObjMap) {
		// getting deep copy using stream api
		this.insertObjMap = insertObjMap != null
				? insertObjMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
				: new HashMap<String, Object>();
	}

//	public void build() {
//		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
//		for (String key : insertObjMap.keySet()) {
//			docBuilder.append(key, insertObjMap.get(key));
//		}
//		insertDbObject = docBuilder.get();
//	}

	public void build() {
		LOGGER.trace("Entered build method");
		document = new Document();
		for (String key : insertObjMap.keySet()) {
			document.append(key, insertObjMap.get(key));
		}
		LOGGER.trace("Leaving build method");
	}

	public Document query() {
		return document;
	}
}
