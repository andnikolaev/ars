package com.epam.andrey_nikolaev.servlets_11.daolayer.dao.oracle;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
	private static ConnectionPool instance;
	private BlockingQueue<Connection> connectionQueue;

	private ConnectionPool(String driverName, String url, String user, String password, final int CONNECTION_POOL_SIZE)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Driver driver = (Driver) Class.forName(driverName).newInstance();
		DriverManager.registerDriver(driver);
		connectionQueue = new ArrayBlockingQueue<>(CONNECTION_POOL_SIZE);
		for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
			Connection connection = DriverManager.getConnection(url, user, password);
			connectionQueue.offer(connection);
		}
	}

	public synchronized static ConnectionPool getInstance(String DRIVER_NAME, String URL, String user, String password,
			int maxConnects) throws Exception {
		if (instance == null) {
			instance = new ConnectionPool(DRIVER_NAME, URL, user, password, maxConnects);
		}
		return instance;
	}

	public ProxyConnection getConnection() throws SQLException, InterruptedException {
		ProxyConnection connection = null;
		connection = new ProxyConnection(connectionQueue.take(), this);
		System.out.println("connection take");
		return connection;
	}

	public void releaseAllConnections() {
		for (Connection connection : connectionQueue) {
			try {
				System.out.println("all connection close");
				connection.close();
			} catch (SQLException e) {
				System.err.println("Problem with closing connection");
			}
		}
		connectionQueue.clear();
	}

	public void releaseConnection(Connection connection) {
		System.out.println("release");
		connectionQueue.offer(connection);
	}
}
