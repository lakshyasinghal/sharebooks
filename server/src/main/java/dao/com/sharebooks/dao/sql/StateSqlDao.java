package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.StateDao;
import com.sharebooks.dao.util.EntityConverterUtility;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.State;

public class StateSqlDao extends AbstractSqlDao implements StateDao {
	private static final Logger LOGGER = Logger.getLogger(StateSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS_MASTER;

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStates() throws SQLException, Exception {
		LOGGER.trace("Entered getStates");
		return EntityConverterUtility.convertIntoStateList(super.getAll(database, Table.STATES, EntityType.STATE));
	}

}
