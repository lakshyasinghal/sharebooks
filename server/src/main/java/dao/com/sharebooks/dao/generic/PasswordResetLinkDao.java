package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.List;

import com.sharebooks.entities.coreEntities.PasswordResetLink;

public interface PasswordResetLinkDao extends Dao {

	public abstract boolean create(PasswordResetLink prl) throws SQLException, Exception;

	public abstract PasswordResetLink get(String uid) throws SQLException, Exception;

	public abstract PasswordResetLink getByLongUrl(String url) throws SQLException, Exception;

	public abstract boolean markExpired(List<String> uids) throws SQLException, Exception;
}
