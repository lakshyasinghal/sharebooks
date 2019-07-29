package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.database.enums.Database;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.SqlDeleteQueryProcessor;
import com.sharebooks.database.sql.SqlInsertQueryProcessor;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.SqlTransactionProcessor;
import com.sharebooks.database.sql.SqlUpdateQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.queries.SqlDeleteQuery;
import com.sharebooks.database.sql.queries.SqlInsertQuery;
import com.sharebooks.database.sql.queries.SqlQuery;
import com.sharebooks.database.sql.queries.SqlReadQuery;
import com.sharebooks.database.sql.queries.SqlUpdateQuery;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;

public abstract class AbstractSqlDao {
	private static final Logger LOGGER = Logger.getLogger(AbstractSqlDao.class);

	public boolean create(Entity entity, Database database, Table table) throws SQLException, Exception {
		LOGGER.trace("Entered create entity " + entity.getClass().getName());
		// get book map
		Map<String, Object> entityMap = entity.map();
		// remove id field and id value from map as it won't be required in insert query
		entityMap.remove("id");
		SqlQuery query = new SqlInsertQuery(table.desc(), entityMap);
		query.build();
		LOGGER.info(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:" + rowsAffected);
		LOGGER.trace("Leaving create entity");
		return rowsAffected > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	public List<Entity> get(Map<String, Object> map, Database database, Table table, EntityType entityType)
			throws SQLException, Exception {
		LOGGER.trace("Entering get method");
		List<Entity> entities = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		// get sql read query
		SqlQuery query = new SqlReadQuery(table.desc(), map);
		query.build();
		entities = (List<Entity>) queryProcessor.processReadQuery(database.desc(), query.toString(), entityType);
		// books = convertIntoBookList(entityList);
		LOGGER.trace("Leaving get method");
		return entities;
	}

	public Entity getFirst(Map<String, Object> map, Database database, Table table, EntityType entityType)
			throws SQLException, Exception {
		LOGGER.trace("Entering get first method");
		List<Entity> entities = get(map, database, table, entityType);
		Entity entity = null;
		if (entities != null && entities.size() > 0) {
			entity = entities.get(0);
		}
		LOGGER.trace("Leaving get first method");
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<Entity> getUsingQuery(String queryStr, Database database, Table table, EntityType entityType)
			throws SQLException, Exception {
		LOGGER.trace("Entering get method");
		List<Entity> entities = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		entities = (List<Entity>) queryProcessor.processReadQuery(database.desc(), queryStr, entityType);
		// books = convertIntoBookList(entityList);
		LOGGER.trace("Leaving get method");
		return entities;
	}

	public List<Entity> getAll(Database database, Table table, EntityType entityType) throws SQLException, Exception {
		LOGGER.trace("Entering getAll");
		List<Entity> entities = get(null, database, table, entityType);
		LOGGER.trace("Leaving getAll");
		return entities;
	}

	public Entity getByUid(String uid, Database database, Table table, EntityType entityType)
			throws SQLException, Exception {
		LOGGER.trace("Entering get bu uid");
		LOGGER.info("uid:" + uid);
		Entity entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		List<Entity> entities = get(map, database, table, entityType);
		if (entities != null && entities.size() > 0) {
			entity = entities.get(0);
		}
		LOGGER.trace("Leaving get by uid");
		return entity;
	}

	public boolean delete(Map<String, Object> map, Database database, Table table) throws SQLException, Exception {
		LOGGER.trace("Entering delete method");
		AbstractSqlQueryProcessor queryProcessor = SqlDeleteQueryProcessor.getInstance();
		SqlQuery query = new SqlDeleteQuery(table.desc(), map);
		query.build();
		int rowsAffected = queryProcessor.processDeleteQuery(database.desc(), query.toString());
		LOGGER.info("Rows Affected:" + rowsAffected);
		LOGGER.trace("Leaving delete method");
		return rowsAffected > 0 ? true : false;
	}

	public boolean deleteByUid(String uid, Database database, Table table) throws SQLException, Exception {
		LOGGER.trace("Entering delete by uid");
		LOGGER.info("uid:" + uid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		boolean deleted = delete(map, database, table);
		LOGGER.info("Deleted:" + deleted);
		LOGGER.trace("Leaving deleteByUid");
		return deleted;
	}

	public boolean update(Map<String, Object> map, Database database, Table table) throws SQLException, Exception {
		LOGGER.trace("Entering update method");
		SqlQuery query = new SqlUpdateQuery(table.desc(), map);
		query.build();
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query.toString());
		LOGGER.trace("Leaving update method");
		return rowsAffected > 0 ? true : false;
	}

	public boolean update(Entity entity, Database database, Table table) throws SQLException, Exception {
		LOGGER.trace("Entering update method");
		Map<String, Object> entityMap = entity.map();
		return update(entityMap, database, table);
	}

	public boolean updateUsingQuery(String queryStr, Database database, Table table) throws SQLException, Exception {
		LOGGER.trace("Entered updateUsingQuery");
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), queryStr);
		LOGGER.trace("Leaving updateUsingQuery");
		return rowsAffected > 0 ? true : false;
	}

	public boolean processTransaction(List<String> queries, Database database) throws SQLException, Exception {
		LOGGER.trace("Entered into process transaction");
		boolean updated = true;
		AbstractSqlQueryProcessor queryProcessor = SqlTransactionProcessor.getInstance();
		// rowsAffectedResults will contain list of results corresponding to each
		// executed query
		List<Integer> rowsAffectedResults = queryProcessor.processTransaction(database.desc(), queries);
		for (Integer rowsAffected : rowsAffectedResults) {
			updated = updated && (rowsAffected > 0);
		}
		LOGGER.trace("Leaving from process transaction");
		return updated;
	}

}
