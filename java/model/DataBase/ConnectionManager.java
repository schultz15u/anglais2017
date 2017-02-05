package model.DataBase;

import java.sql.*;


public class ConnectionManager {
	
	private static String protocol = "jdbc:sqlite:";
	private String name = "DataBase.db";
	private Connection connection = null;

	public ConnectionManager() {
		try {
			connection = DriverManager.getConnection(protocol + name);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Opened database successfully");
		new TableCreate(connection);
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void disconnect() {
		if (connection != null) {
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("   Closed database connection successfully");
		}
	}
}