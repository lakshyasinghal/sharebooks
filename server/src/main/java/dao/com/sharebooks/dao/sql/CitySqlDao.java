package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.CityDao;
import com.sharebooks.dao.util.EntityConverterUtility;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.City;

public class CitySqlDao extends AbstractSqlDao implements CityDao {
	private static final Logger LOGGER = Logger.getLogger(CitySqlDao.class.getName());
	private final Database database = Database.MASTER;

	@Override
	public List<City> getCities() throws SQLException, Exception {
		LOGGER.trace("Entered getCities");
		return EntityConverterUtility.convertIntoCityList(super.getAll(database, Table.CITIES, EntityType.CITY));
	}

	@Override
	public boolean insert(City city) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(List<City> cities) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
