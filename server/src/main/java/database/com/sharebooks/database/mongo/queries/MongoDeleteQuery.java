package com.sharebooks.database.mongo.queries;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class MongoDeleteQuery {
	private static final Logger LOGGER = Logger.getLogger(MongoDeleteQuery.class.getName());
	private final Map<String, Object> queryObjMap;
	private DBObject queryDbObject;

	public MongoDeleteQuery(Map<String, Object> queryObjMap) {
		this.queryObjMap = queryObjMap;
	}

	public void build() {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		for (String key : queryObjMap.keySet()) {
			docBuilder.append(key, queryObjMap.get(key));
		}
		queryDbObject = docBuilder.get();
	}

	public DBObject query() {
		return queryDbObject;
	}

}
