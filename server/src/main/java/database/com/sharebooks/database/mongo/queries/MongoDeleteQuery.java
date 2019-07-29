package com.sharebooks.database.mongo.queries;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoDeleteQuery {
	private static final Logger LOGGER = Logger.getLogger(MongoDeleteQuery.class.getName());
	private final Map<String, Object> queryObjMap;
	// private DBObject queryDbObject;
	private Bson filter;

	public MongoDeleteQuery(Map<String, Object> queryObjMap) {
		// getting deep copy using stream api
		this.queryObjMap = queryObjMap != null
				? queryObjMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
				: new HashMap<String, Object>();
	}

	public void build() {
		filter = new Document();
		for (String key : queryObjMap.keySet()) {
			((Document) filter).append(key, queryObjMap.get(key));
		}
	}

	public Bson filter() {
		return filter;
	}

}
