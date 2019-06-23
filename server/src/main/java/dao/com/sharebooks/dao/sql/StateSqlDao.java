package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.AbstractStateDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.entities.helperEntities.State;

public class StateSqlDao extends AbstractStateDao {
	private static final Logger LOGGER = Logger.getLogger(StateSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS_MASTER;

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStates() throws SQLException, Exception {
		LOGGER.trace("Entered getStates");
		List<State> states = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		// get sql read query
		SqlQuery query = new SqlReadQuery(Table.STATES.desc(), null);
		query.build();
		LOGGER.info(query.toString());
		List<Entity> entityList = (List<Entity>) queryProcessor.processReadQuery(database.desc(), query.toString(),
				EntityType.STATE);
		states = convertIntoStateList(entityList);
		LOGGER.trace("Leaving getStates");
		return states;
	}

}
