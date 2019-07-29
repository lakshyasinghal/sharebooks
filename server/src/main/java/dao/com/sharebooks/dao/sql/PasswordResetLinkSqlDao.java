package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.PasswordResetLinkDao;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.PasswordResetLinkQueries;
import com.sharebooks.entities.coreEntities.PasswordResetLink;
import com.sharebooks.entities.coreEntities.enums.EntityType;

public class PasswordResetLinkSqlDao extends AbstractSqlDao implements PasswordResetLinkDao {
	private static final Logger LOGGER = Logger.getLogger(PasswordResetLinkSqlDao.class);
	// private EntityFactory<User> factory;
	private final Database database = Database.USER_ACCOUNTS;
	private final Table table = Table.PASSWORD_RESET_LINKS;
	private final PasswordResetLinkQueries prlQueries = PasswordResetLinkQueries.instance();

	@Override
	public boolean create(PasswordResetLink prl) throws SQLException, Exception {
		return super.create(prl, database, table);
	}

	@Override
	public PasswordResetLink get(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering get method");
		LOGGER.trace("uid:" + uid);
		PasswordResetLink prl = null;
		try {
			prl = (PasswordResetLink) super.getByUid(uid, database, table, EntityType.PASSWORD_RESET_LINK);
		} catch (Exception ex) {
		}
		return prl;
	}

	@Override
	public PasswordResetLink getByLongUrl(String url) throws SQLException, Exception {
		LOGGER.trace("Entering getByLongUrl");
		LOGGER.trace("url:" + url);
		PasswordResetLink prl = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("longUrl", url);
		try {
			prl = (PasswordResetLink) super.getFirst(map, database, table, EntityType.PASSWORD_RESET_LINK);
		} catch (Exception ex) {
		}
		return prl;
	}

	@Override
	public boolean markExpired(List<String> uids) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
