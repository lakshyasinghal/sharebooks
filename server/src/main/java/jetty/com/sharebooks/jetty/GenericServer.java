package com.sharebooks.jetty;



public interface GenericServer extends Runnable{
	
	default void run(){
		try{
			start();
		}
		catch(Exception e){
			System.out.println("Error occurred in server thread.");
		}
	}
	
	ServerStatus status();
	
	void start() throws Exception;
	
	void stop() throws Exception;
	
	int port();
}
