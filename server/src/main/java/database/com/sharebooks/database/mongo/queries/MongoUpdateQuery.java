package com.sharebooks.database.mongo.queries;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class MongoUpdateQuery {
	private static final Logger LOGGER = Logger.getLogger(MongoUpdateQuery.class.getName());
	private final Map<String, Object> queryObjMap;
	private final Map<String, Object> updateObjMap;

	private DBObject queryDBObject;
	private DBObject updateDBObject;

	public MongoUpdateQuery(Map<String, Object> queryObjMap, Map<String, Object> updateObjMap) {
		this.queryObjMap = queryObjMap;
		this.updateObjMap = updateObjMap;
	}

	public void build() {
		LOGGER.trace("Entered build method");
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		for (String key : queryObjMap.keySet()) {
			docBuilder.append(key, queryObjMap.get(key));
		}
		queryDBObject = docBuilder.get();

		docBuilder = BasicDBObjectBuilder.start();
		for (String key : updateObjMap.keySet()) {
			docBuilder.append(key, updateObjMap.get(key));
		}
		updateDBObject = docBuilder.get();
		LOGGER.trace("Leaving build method");
	}

	public DBObject queryObj() {
		return queryDBObject;
	}

	public DBObject updateObj() {
		return updateDBObject;
	}
}
