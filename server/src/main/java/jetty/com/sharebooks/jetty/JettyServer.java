package com.sharebooks.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.sharebooks.rest.jersey.JerseyResourceConfig;

public class JettyServer implements GenericServer {
	private Server server;
	private final int port;
	private ServerStatus status;
	
	
	public JettyServer(int port){
		this.port = port;
	}
	
	
	public void start() throws Exception {
		status = ServerStatus.STARTING;
		ServletContainer container = new ServletContainer(new JerseyResourceConfig());
		ServletHolder sh = new ServletHolder(container);
		ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
		   errorHandler.addErrorPage(404, "/error");
		   errorHandler.addErrorPage(500, "/error");
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS
				| ServletContextHandler.NO_SECURITY);
		context.setContextPath("/");
		context.addServlet(sh, "/*");
		context.setErrorHandler(errorHandler);
		server = new Server();
		HttpConfiguration httpConfiguration = new HttpConfiguration();

		ServerConnector httpConnector = new ServerConnector(server, new HttpConnectionFactory(httpConfiguration));
		httpConnector.setPort(port);
		httpConnector.setIdleTimeout(30000); // TODO : Default was 30000
		Connector[] connectors = new Connector[] { httpConnector };

		server.setConnectors(connectors);
		server.setHandler(context);
		server.start();
		status = ServerStatus.STARTED;
		System.out.println("Server started on port 9000");
		server.join();
	}
	
	@Override
	public void stop() throws Exception {
		status = ServerStatus.STOPPING;
		server.stop();
		status = ServerStatus.STOPPED;
		//logger.info("Jetty server has stopped");
	}

	@Override
	public int port() {
		return port;
	}

	@Override
	public ServerStatus status() {
		// TODO Auto-generated method stub
		return null;
	}
}
