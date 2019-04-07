package com.sharebooks.init;

import com.sharebooks.jetty.GenericServer;
import com.sharebooks.jetty.JettyServer;
import com.sharebooks.test.util.DummyPropertySource;

public class Launcher {
	
	
	public static void main(String[] args){
		Launcher launcher = new Launcher();
		launcher.launch();
	}
	
	public void initialize(){
		AppInitializer appInitializer = new AppInitializer();
		appInitializer.initialize(DummyPropertySource.getPropertyMap());
	}
	
	
	public void launch(){
		try{
			initialize();
			GenericServer server = new JettyServer(9000);
			server.start();
			System.out.println("App started");
		}
		catch(Exception e){
			
		}
	}
}
