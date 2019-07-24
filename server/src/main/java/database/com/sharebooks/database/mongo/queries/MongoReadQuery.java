package com.sharebooks.database.mongo.queries;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class MongoReadQuery {
	private static final Logger LOGGER = Logger.getLogger(MongoDeleteQuery.class.getName());
	private final Map<String, Object> queryObjMap;
	private DBObject queryDbObject;

	public MongoReadQuery(Map<String, Object> queryObjMap) {
		this.queryObjMap = queryObjMap;
	}

	public void build() {
		LOGGER.trace("Entered build method");
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		for (String key : queryObjMap.keySet()) {
			docBuilder.append(key, queryObjMap.get(key));
		}
		queryDbObject = docBuilder.get();
		LOGGER.trace("Leaving build method");
	}

	public DBObject query() {
		return queryDbObject;
	}
}
