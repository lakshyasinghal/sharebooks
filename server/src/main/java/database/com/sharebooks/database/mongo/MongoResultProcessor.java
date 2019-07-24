package com.sharebooks.database.mongo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.sources.FactorySource;

public class MongoResultProcessor {
	private static MongoResultProcessor resultProcessor = new MongoResultProcessor();

	private MongoResultProcessor() {

	}

	public static MongoResultProcessor getInstance() {
		return resultProcessor;
	}

	public List<? extends Entity> process(DBCursor cursor, EntityType entityType) throws SQLException, Exception {
		List<Entity> entityList = new ArrayList<Entity>();
		EntityFactory<? extends Entity> factory = FactorySource.getEntityFactory(entityType.desc());

		while (cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			entityList.add(factory.createFromMongoDatabaseObject(dbObj));
		}
		return entityList;
	}
}
