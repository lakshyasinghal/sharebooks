package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.AbstractPasswordResetLinkDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlInsertQueryProcessor;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.PasswordResetLinkQueries;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.entities.coreEntities.PasswordResetLink;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;

public class PasswordResetLinkSqlDao extends AbstractPasswordResetLinkDao {
	private static final Logger LOGGER = Logger.getLogger(PasswordResetLinkSqlDao.class);
	// private EntityFactory<User> factory;
	private final Database database = Database.SHAREBOOKS_USER_ACCOUNTS;
	private final Table table = Table.PASSWORD_RESET_LINKS;
	private final PasswordResetLinkQueries prlQueries = PasswordResetLinkQueries.instance();

	@Override
	public boolean create(PasswordResetLink prl) throws SQLException, Exception {
		Map<String, Object> prlMap = prl.map();
		// remove id field and id value from map as it won't be required in insert query
		prlMap.remove("id");
		SqlQuery query = new SqlInsertQuery(table.desc(), prlMap);
		query.build();
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:" + rowsAffected);
		// LOGGER.exiting("UserSqlDao", "insertUser");
		return rowsAffected > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PasswordResetLink get(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering get method");
		LOGGER.trace("uid:" + uid);
		PasswordResetLink prl = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		SqlQuery query = new SqlReadQuery(table.desc(), map);
		query.build();
		List<Entity> entityList = (List<Entity>) queryProcessor.processReadQuery(database.desc(), query.toString(),
				EntityType.PASSWORD_RESET_LINK);
		List<PasswordResetLink> prls = convertIntoLinksList(entityList);
		if (prls != null && prls.size() > 0) {
			prl = prls.get(0);
		}
		LOGGER.trace("Leaving get");
		return prl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PasswordResetLink getByLongUrl(String url) throws SQLException, Exception {
		LOGGER.trace("Entering getByLongUrl");
		LOGGER.trace("url:" + url);
		PasswordResetLink prl = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("longUrl", url);
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		SqlQuery query = new SqlReadQuery(table.desc(), map);
		query.build();
		List<Entity> entityList = (List<Entity>) queryProcessor.processReadQuery(database.desc(), query.toString(),
				EntityType.PASSWORD_RESET_LINK);
		List<PasswordResetLink> prls = convertIntoLinksList(entityList);
		if (prls != null && prls.size() > 0) {
			prl = prls.get(0);
		}
		LOGGER.trace("Leaving getByLongUrl");
		return prl;
	}

	@Override
	public boolean markExpired(List<String> uids) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
