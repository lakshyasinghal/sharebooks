package com.sharebooks.test.connectionPool;

import java.sql.Connection;
import com.sharebooks.connectionPool.ConnectionPool;
import com.sharebooks.sources.ConnectionPoolSource;
import com.sharebooks.test.AbstractTester;
import java.util.*;

public class ConnectionPoolTester extends AbstractTester{

	
	public static void main(String[] args){
		ConnectionPoolTester tester = new ConnectionPoolTester();
		tester.test();
	}
	
	public void test() {
		initializeApp();
		test1();
		test2();
	}
	
	
	//connection pool tested
	private void test1(){
		ConnectionPool pool = ConnectionPoolSource.getConnectionPoolMap().get("sharebooks");
		display("Pool capacity : " + pool.capacity());
		newline();
		ConnectionConsumer consumer = new ConnectionConsumer(pool);
		
		Thread[] threads = new Thread[6];
		for(int i=0 ; i<threads.length ; i++){
			threads[i] = new Thread(consumer , "Thread " + (i+1));
			threads[i].start();
		}
	}
	
	private void test2(){
		
	}
}



class ConnectionConsumer implements Runnable{
	private ConnectionPool pool;
	
	ConnectionConsumer(ConnectionPool pool){
		this.pool = pool;
	}

	public void run() {
		try {
			int i=0;
			Random random = new Random();
			while(i<3){
				Connection conn = pool.getSqlConnection();
				if(conn!=null){
					System.out.println(Thread.currentThread().getName() + " got connection");
				}
				else{
					System.out.println(Thread.currentThread().getName() + " got null");
				}
				Thread.sleep(random.nextInt(100));
				pool.releaseSqlConnection(conn);
				i++;
			}
			synchronized(ConnectionConsumer.class){
				System.out.println();
				System.out.println(Thread.currentThread().getName() + " is done.");
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
