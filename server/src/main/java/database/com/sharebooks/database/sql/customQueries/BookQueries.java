package com.sharebooks.database.sql.customQueries;

public class BookQueries {
	
	private final String searchByCategory = "SELECT * FROM BOOKS WHERE CATEGORY LIKE '%?%'";
}
