package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.AbstractCityDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.entities.helperEntities.City;

public class CitySqlDao extends AbstractCityDao{
	private static final Logger LOGGER = Logger.getLogger(CitySqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS;
	
	@Override
	public List<City> getCities() throws SQLException, Exception {
		LOGGER.trace("Entered getCities");
		List<City> cities = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		//get sql read query
		SqlQuery query = new SqlReadQuery(Table.CITIES.desc() , null);
		query.build();
		LOGGER.info(query.toString());
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.CITY);
		cities = convertIntoCityList(entityList);
		LOGGER.trace("Leaving getCities");
		return cities;
	}
}
