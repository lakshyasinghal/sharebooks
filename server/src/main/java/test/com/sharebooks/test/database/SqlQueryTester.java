package com.sharebooks.test.database;

import java.util.HashMap;
import java.util.*;

//import com.mysql.fabric.xmlrpc.base.Array;
import com.sharebooks.database.sql.query.SqlDeleteQuery;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.test.AbstractTester;

public class SqlQueryTester extends AbstractTester{
	//private int passed = 0;
	//private int failed = 0;
	
	public static void main(String[] args){
		SqlQueryTester queryTester = new SqlQueryTester();
		queryTester.test();
	}
	
	public void test(){
		ReadQueryTester readQueryTester = new ReadQueryTester();
		DeleteQueryTester deleteQueryTester = new DeleteQueryTester();
		InsertQueryTester insertQueryTester = new InsertQueryTester();
		
		readQueryTester.test();
		deleteQueryTester.test();
		insertQueryTester.test();
	}
	
	
	
	private class ReadQueryTester extends AbstractTester{
		//public final static String className = "ReadQueryTester";
		
		public void test(){
			test1();
			test2();
		}
		
		private void test1(){
			SqlReadQuery query = new SqlReadQuery("users" , null);
			display(query.toString());
		}
		
		private void test2(){
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("name", "lakshya singhal");
			map.put("age", 28);
			SqlReadQuery query = new SqlReadQuery("users" , map);
			display(query.toString());
		}
	}
	
	private class DeleteQueryTester extends AbstractTester{
		//public final static String className = "DeleteQueryTester";
		
		public void test(){
			test1();
			test2();
		}
		
		private void test1(){
			SqlDeleteQuery query = new SqlDeleteQuery("books" , null);
			display(query.toString());
		}
		
		private void test2(){
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("title", "Concepts of Physics");
			map.put("authorName", "H.C Verma");
			map.put("available", 0);
			SqlDeleteQuery query = new SqlDeleteQuery("books" , map);
			display(query.toString());
		}
	}
	
	private class InsertQueryTester extends AbstractTester{
		//public final static String className = "InsertQueryTester";
		
		public void test(){
			test1();
			test2();
		}
		
		private void test1(){
			String table = "Books";
			List<String> fields = new ArrayList<String>();
			List<Object> values = new ArrayList<Object>();
			//adding fields
			fields.add("id");
			fields.add("title");
			fields.add("authorName");
			fields.add("category");
			fields.add("subcategory");
			fields.add("ownerId");
			fields.add("available");
			fields.add("forBuying");
			fields.add("forRent");
			fields.add("buyingAmount");
			fields.add("rentAmount");
			//adding values
			values.add(2);
			values.add("Concepts Of Physics");
			values.add("H.C Verma");
			values.add("Science");
			values.add("Physics");
			values.add(10);
			values.add(1);
			values.add(1);
			values.add(1);
			values.add(280);
			values.add(3);
			
			SqlInsertQuery query = new SqlInsertQuery(table, fields, values);
			display(query.toString());
		}
		
		private void test2(){
			
		}
	}
	
	@SuppressWarnings("unused")
	private class UpdateQueryTester extends AbstractTester{
		
		
		public void test(){
			test1();
			test2();
		}
		
		private void test1(){
			
		}
		
		private void test2(){
			
		}
	}
	
}
