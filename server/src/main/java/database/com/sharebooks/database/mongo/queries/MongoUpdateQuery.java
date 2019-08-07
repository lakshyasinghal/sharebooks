package com.sharebooks.database.mongo.queries;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoUpdateQuery {
	private static final Logger LOGGER = Logger.getLogger(MongoUpdateQuery.class.getName());
	private final Map<String, Object> queryObjMap;
	private final Map<String, Object> updateObjMap;

//	private DBObject queryDBObject;
//	private DBObject updateDBObject;

	private Bson filter;
	private Bson updateDoc;

	public MongoUpdateQuery(Map<String, Object> queryObjMap, Map<String, Object> updateObjMap) {
		// getting deep copy of maps using stream apis
		this.queryObjMap = queryObjMap != null
				? queryObjMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
				: new HashMap<String, Object>();
		this.updateObjMap = updateObjMap != null
				? updateObjMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
				: new HashMap<String, Object>();
	}

//	public void build() {
//		LOGGER.trace("Entered build method");
//		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
//		for (String key : queryObjMap.keySet()) {
//			docBuilder.append(key, queryObjMap.get(key));
//		}
//		queryDBObject = docBuilder.get();
//
//		docBuilder = BasicDBObjectBuilder.start();
//		for (String key : updateObjMap.keySet()) {
//			docBuilder.append(key, updateObjMap.get(key));
//		}
//		updateDBObject = docBuilder.get();
//		LOGGER.trace("Leaving build method");
//	}

	public void build() {
		LOGGER.trace("Entered build method");
		filter = new Document();
		for (String key : queryObjMap.keySet()) {
			((Document) filter).append(key, queryObjMap.get(key));
		}

		Bson newValue = new Document();
		for (String key : updateObjMap.keySet()) {
			((Document) newValue).append(key, updateObjMap.get(key));
		}
		updateDoc = new Document("$set", newValue);

		LOGGER.trace("Leaving build method");
	}

	public Bson filter() {
		return filter;
	}

	public Bson updateDoc() {
		return updateDoc;
	}
}
