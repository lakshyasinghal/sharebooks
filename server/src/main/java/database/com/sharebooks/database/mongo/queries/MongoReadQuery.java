package com.sharebooks.database.mongo.queries;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoReadQuery {
	private static final Logger LOGGER = Logger.getLogger(MongoReadQuery.class.getName());
	private final Map<String, Object> queryObjMap;
	// private DBObject queryDbObject;
	private Bson filter;

	public MongoReadQuery(Map<String, Object> queryObjMap) {
		// deep copy using stream api
		this.queryObjMap = queryObjMap != null
				? queryObjMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
				: new HashMap<String, Object>();
	}

//	public void build() {
//		LOGGER.trace("Entered build method");
//		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
//		for (String key : queryObjMap.keySet()) {
//			docBuilder.append(key, queryObjMap.get(key));
//		}
//		queryDbObject = docBuilder.get();
//		LOGGER.trace("Leaving build method");
//	}

	public void build() {
		LOGGER.trace("Entered build method");
		filter = new Document();
		for (String key : queryObjMap.keySet()) {
			((Document) filter).append(key, queryObjMap.get(key));
		}
		LOGGER.trace("Leaving build method");
	}

	public Bson filter() {
		return filter;
	}
}
