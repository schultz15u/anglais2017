package model.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionManager {

	private static String protocol = "jdbc:sqlite:";
	private String name = "DataBase.db";
	private Connection connection = null;

	private static ConnectionManager instance = new ConnectionManager();

	private ConnectionManager() {
		try {
			boolean needCreation = false;
			if (!new File(name).exists()) {
				needCreation = true;
			}
			connection = DriverManager.getConnection(protocol + name);
			connection.setAutoCommit(true);
			if (needCreation) {
				creation("DataBase.sql");
				creation("1_tenses.sql");
				creation("3_modals.sql");
				creation("4_linking_verbs.sql");
				creation("5_verb_complementation.sql");
			}
		} catch (SQLException | FileNotFoundException e) {
			new File(name).deleteOnExit();
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static ConnectionManager getInstance() {
		return instance;
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

	private void creation(String fichierBD) throws FileNotFoundException, SQLException {
		Scanner s = new Scanner(new File(fichierBD));
		String sql = "";
		String curr = "";
		while (s.hasNext()) {
			sql = "";
			curr = "";
			while (s.hasNext() && !curr.contains(";")) {
				curr = s.nextLine() + "\n";
				sql += curr;
			}
			curr = curr.trim();
			System.out.println(sql);
			connection.createStatement().execute(sql);
		}
		s.close();
	}
}