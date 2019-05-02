package com.sharebooks.database.sql.customQueries;


import org.apache.log4j.Logger;
import com.sharebooks.database.sql.Table;

public final class BookQueries {
	private static final Logger LOGGER = Logger.getLogger(BookQueries.class.getName());
	private static final BookQueries instance = new BookQueries();
	
	private BookQueries(){
		
	}
	
	public static BookQueries instance(){
		return instance;
	}
	
	
	public String getQueryForSearchTerm(String searchTerm){
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ");
		query.append(Table.BOOKS.desc());
		query.append(" WHERE ");
		query.append("TITLE LIKE ");
		query.append("'%" + searchTerm + "%'");
		query.append(" OR ");
		query.append("AUTHOR LIKE ");
		query.append("'%" + searchTerm + "%'");
		query.append(" OR ");
		query.append("CATEGORY LIKE ");
		query.append("'%" + searchTerm + "%'");
		query.append(" OR ");
		query.append("SUBCATEGORY LIKE ");
		query.append("'%" + searchTerm + "%'");
		query.append(";");
		LOGGER.debug("Query => " + query.toString());
		return query.toString();
	}
	
	public String getByCategoryQuery(String category){
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ");
		query.append(Table.BOOKS.desc());
		query.append(" WHERE ");
		query.append("CATEGORY=");
		query.append("'" + category + "'");
		query.append(";");
		LOGGER.debug("Query => " + query.toString());
		return query.toString();
	}
	
	public String getSimilarBooksQuery(String title){
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ");
		query.append(Table.BOOKS.desc());
		query.append(" WHERE ");
		query.append("TITLE=");
		query.append("'" + title + "'");
		query.append(";");
		LOGGER.debug("Query => " + query.toString());
		return query.toString();
	}
}
