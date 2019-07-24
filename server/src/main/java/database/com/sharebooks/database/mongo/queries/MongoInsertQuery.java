package com.sharebooks.database.mongo.queries;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class MongoInsertQuery {
	private static final Logger LOGGER = Logger.getLogger(MongoInsertQuery.class.getName());
	private final Map<String, Object> insertObjMap;
	// private Document document;
	private DBObject insertDbObject;

	public MongoInsertQuery(Map<String, Object> insertObjMap) {
		this.insertObjMap = insertObjMap;
	}

	public void build() {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		for (String key : insertObjMap.keySet()) {
			docBuilder.append(key, insertObjMap.get(key));
		}
		insertDbObject = docBuilder.get();
	}

//	public void build() {
//		document = new Document();
//
//		document.append("id", 1).append("description", "database").append("likes", 100)
//				.append("url", "http://www.tutorialspoint.com/mongodb/").append("by", "tutorials point");
//	}

	public DBObject query() {
		return insertDbObject;
	}
}
