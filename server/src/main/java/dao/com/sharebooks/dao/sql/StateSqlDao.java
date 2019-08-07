package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.StateDao;
import com.sharebooks.dao.util.EntityConverterUtility;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.State;

public class StateSqlDao extends AbstractSqlDao implements StateDao {
	private static final Logger LOGGER = Logger.getLogger(StateSqlDao.class.getName());
	private final Database database = Database.MASTER;

	@Override
	public List<State> getStates() throws SQLException, Exception {
		LOGGER.trace("Entered getStates");
		return EntityConverterUtility.convertIntoStateList(super.getAll(database, Table.STATES, EntityType.STATE));
	}

	@Override
	public boolean insert(State state) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(List<State> states) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
