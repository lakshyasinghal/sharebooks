package com.sharebooks.connectionPool;

import static com.sharebooks.util.messages.ExceptionMessages.CONNECTION_POOL_NOT_WORKING_PROPERLY;
import static com.sharebooks.util.messages.InfoMessages.DATABASE_CONNECTION_CREATED;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.sharebooks.exception.ConnectionPoolException;
import com.sharebooks.factory.dbConnectionFactory.DBConnFactory;

public class SqlConnectionPool implements ConnectionPool {
	private static final Logger LOGGER = Logger.getLogger(SqlConnectionPool.class.getName());

	private String host;
	private String port;
	private String name; // database name
	private String username;
	private String password;
	private Connection[] connections;
	private int[] connStatusArray;
	private int capacity;
	private int size;
	private DBConnFactory connFactory;

	public SqlConnectionPool() {
		// nothing
	}

	public SqlConnectionPool(String host, String port, String name, String username, String password, int capacity,
			DBConnFactory connFactory) {
		this.host = host;
		this.port = port;
		this.name = name;
		this.username = username;
		this.password = password;
		this.capacity = capacity;
		connections = new Connection[capacity];
		connStatusArray = new int[capacity];
		this.connFactory = connFactory;
	}

	public void init(int capacity) throws Exception {

	}

	@Override
	public Connection getSqlConnection() throws ConnectionPoolException, Exception {
		// LOGGER.entering(getClass().getName(), "getConnection");
		Connection conn = null;
		synchronized (this) {
			conn = getAvailableConnection();
			if (conn == null) {
				// if connection pool is full, wait for some connection to be released
				if (isFull()) {
					LOGGER.info("Connection pool full.");
					while (!isConnectionAvailable()) {
						// wait until notified by release connection method
						LOGGER.info(Thread.currentThread().getName() + " going in waiting state.");
						this.wait();
						LOGGER.info(Thread.currentThread().getName() + " resumes from waiting state.");
					}
				} else { // add new connection to the connection pool
					addNewConnection();
				}
				conn = getAvailableConnection();
				if (conn == null) {
					// displayState();
					throw new ConnectionPoolException(CONNECTION_POOL_NOT_WORKING_PROPERLY);
				}
			}
		}
		// LOGGER.exiting(getClass().getName(), "getConnection");
		return conn;
	}

	// this method will add new sql connection to the pool in case the connection
	// pool is not operating at its max capacity
	private synchronized void addNewConnection() throws ConnectionPoolException, Exception {
		// LOGGER.entering(getClass().getName(), "addNewConnection");
		if (isFull()) {
			throw new ConnectionPoolException(CONNECTION_POOL_NOT_WORKING_PROPERLY);
		} else {
			Connection connection = connFactory.getSqlConnection(host, port, name, username, password);
			connections[size] = connection;
			connStatusArray[size] = 1;
			size++;
			LOGGER.info(DATABASE_CONNECTION_CREATED);
			LOGGER.info("Connection pool size:" + size);
		}
		// LOGGER.exiting(getClass().getName(), "addNewConnection");
	}

	private synchronized Connection getAvailableConnection() throws Exception {
		// LOGGER.entering(getClass().getName(), "addNewConnection");
		for (int i = 0; i < size; i++) {
			if (connStatusArray[i] == 1) {
				connStatusArray[i] = 0;
				return connections[i];
			}
		}
		return null;
	}

	// The thread occupying sql connection will enter this method to release the
	// connection and notify any of the waiting threads
	@Override
	public void releaseSqlConnection(Connection conn) throws Exception {
		// LOGGER.entering(getClass().getName(), "releaseConnection");
		synchronized (this) {
			for (int i = 0; i < size; i++) {
				if (connections[i].equals(conn)) {
					connStatusArray[i] = 1;
					break;
				}
			}
			// will notify only the first thread as only one connection will be released
			this.notify();
			LOGGER.info(Thread.currentThread().getName() + " has notified other threads.");
		}
		// LOGGER.exiting(getClass().getName(), "releaseConnection");
	}

	private boolean isConnectionAvailable() throws Exception {
		boolean isAvailable = false;
		for (int i = 0; i < size; i++) {
			if (connStatusArray[i] == 1) {
				isAvailable = true;
				break;
			}
		}
		return isAvailable;
	}

	private boolean isFull() {
		return size == capacity;
	}

	public int capacity() {
		return capacity;
	}
}
