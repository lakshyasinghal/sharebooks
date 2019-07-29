package com.sharebooks.dao.mongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.database.enums.Database;
import com.sharebooks.database.mongo.Collection;
import com.sharebooks.database.mongo.MongoDeleteQueryProcessor;
import com.sharebooks.database.mongo.MongoInsertQueryProcessor;
import com.sharebooks.database.mongo.MongoReadQueryProcessor;
import com.sharebooks.database.mongo.MongoUpdateQueryProcessor;
import com.sharebooks.database.mongo.queries.MongoDeleteQuery;
import com.sharebooks.database.mongo.queries.MongoInsertQuery;
import com.sharebooks.database.mongo.queries.MongoReadQuery;
import com.sharebooks.database.mongo.queries.MongoUpdateQuery;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.exception.NonFunctionalMethodException;

public class AbstractMongoDao {
	private static final Logger LOGGER = Logger.getLogger(AbstractMongoDao.class);

	public boolean create(Entity entity, Database database, Collection collection) throws Exception {
		LOGGER.trace("Entered create method");
		LOGGER.debug("Creating entity " + entity.getClass().getName());
		Map<String, Object> entityMap = entity.map();
		// remove id field and id value from map as it won't be required in insert query
		entityMap.remove("id");
		MongoInsertQuery query = new MongoInsertQuery(entityMap);
		MongoInsertQueryProcessor queryProcessor = MongoInsertQueryProcessor.instance();
		int rowsAffected = queryProcessor.process(database.desc(), collection.desc(), query);
		LOGGER.info("Rows Affected:" + rowsAffected);
		LOGGER.trace("Leaving create method");
		return rowsAffected > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	public List<Entity> get(Map<String, Object> map, Database database, Collection collection, EntityType entityType)
			throws Exception {
		LOGGER.trace("Entering get method");
		List<Entity> entities = null;
		MongoReadQueryProcessor queryProcessor = MongoReadQueryProcessor.instance();
		MongoReadQuery query = new MongoReadQuery(map);
		entities = (List<Entity>) queryProcessor.process(database.desc(), collection.desc(), query, entityType);
		LOGGER.trace("Leaving get method");
		return entities;
	}

	public Entity getFirst(Map<String, Object> map, Database database, Collection collection, EntityType entityType)
			throws Exception {
		LOGGER.trace("Entering get first method");
		List<Entity> entities = get(map, database, collection, entityType);
		Entity entity = null;
		if (entities != null && entities.size() > 0) {
			entity = entities.get(0);
		}
		LOGGER.trace("Leaving get first method");
		return entity;
	}

	public List<Entity> getAll(Database database, Collection collection, EntityType entityType) throws Exception {
		LOGGER.trace("Entering getAll");
		List<Entity> entities = get(null, database, collection, entityType);
		LOGGER.trace("Leaving getAll");
		return entities;
	}

	public Entity getByUid(String uid, Database database, Collection collection, EntityType entityType)
			throws Exception {
		LOGGER.trace("Entering get by uid");
		LOGGER.info("uid:" + uid);
		Entity entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		List<Entity> entities = get(map, database, collection, entityType);
		if (entities != null && entities.size() > 0) {
			entity = entities.get(0);
		}
		LOGGER.trace("Leaving get by uid");
		return entity;
	}

	public boolean delete(Map<String, Object> map, Database database, Collection collection) throws Exception {
		LOGGER.trace("Entering delete method");
		MongoDeleteQueryProcessor queryProcessor = MongoDeleteQueryProcessor.instance();
		MongoDeleteQuery query = new MongoDeleteQuery(map);
		int rowsAffected = queryProcessor.process(database.desc(), collection.desc(), query);
		LOGGER.info("Rows Affected:" + rowsAffected);
		LOGGER.trace("Leaving delete method");
		return rowsAffected > 0 ? true : false;
	}

	public boolean deleteByUid(String uid, Database database, Collection collection) throws Exception {
		LOGGER.trace("Entering delete by uid");
		LOGGER.info("uid:" + uid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		boolean deleted = delete(map, database, collection);
		LOGGER.info("Deleted:" + deleted);
		LOGGER.trace("Leaving deleteByUid");
		return deleted;
	}

	public boolean update(Map<String, Object> queryMap, Map<String, Object> updateMap, Database database,
			Collection collection) throws Exception {
		LOGGER.trace("Entering update method");
		MongoUpdateQuery query = new MongoUpdateQuery(queryMap, updateMap);
		MongoUpdateQueryProcessor queryProcessor = MongoUpdateQueryProcessor.instance();
		int rowsAffected = queryProcessor.process(database.desc(), collection.desc(), query);
		LOGGER.trace("Leaving update method");
		return rowsAffected > 0 ? true : false;
	}

	public boolean update(Entity entity, Database database, Collection collection) throws Exception {
		LOGGER.trace("Entering update method");
		// will put uid in a query map and rest key values in a separate map
		Map<String, Object> entityMap = entity.map();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Map<String, Object> updateMap = null;
		queryMap.put("uid", entityMap.get("uid"));
		entityMap.remove("uid");
		// the rest of the entity map can be treated as updation map
		updateMap = entityMap;
		return update(queryMap, updateMap, database, collection);
	}

	public boolean updateByUid(String uid, Map<String, Object> updateMap, Database database, Collection collection)
			throws Exception {
		LOGGER.trace("Entering update method");
		// will put uid in a query map and rest key values in a separate map
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("uid", uid);
		return update(queryMap, updateMap, database, collection);
	}

	public boolean updateUsingQuery() throws Exception {
		throw new NonFunctionalMethodException();
	}

	public boolean processTransaction() throws Exception {
		throw new NonFunctionalMethodException();
	}

}
