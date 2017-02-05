package model.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreate {

	private Connection connection;
	
	public TableCreate(Connection c) {
		connection = c;
		createTable();
	}
	
	private void createTable() {
		createTableUser();
		createTableSubscribe();
		createTableVideo();
		createTableComment();
		createTableCollection();
		createTableCompositionCollection();	
	}
	//creation de toutes les tables...
	private void createTableUser() {
		String table = "CREATE TABLE IF NOT EXISTS USER"
						+ "(id_user INTEGER,"
						+ "username VARCHAR(50),"
						+ "mail VARCHAR(50),"
						+ "password VARCHAR(50),"
						+ "id_comment NUMBER(25),"
						+ "id_collection INTEGER,"	
						+ "PRIMARY KEY (id_user))";	
		
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table USER created successfully");
	}
	
	private void createTableSubscribe() {
		String table = "CREATE TABLE IF NOT EXISTS SUBSCRIBE"
						+ "(id_user INTEGER,"
						+ "id_user_1 NUMBER(25),"
						+ "PRIMARY KEY (id_user, id_user_1),"
						+ "FOREIGN KEY (id_user) REFERENCES PERSONNE (id_user),"
						+ "FOREIGN KEY (id_user_1) REFERENCES PERSONNE (id_user))";		
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table SUBSCRIBE created successfully");
	}
	
	private void createTableVideo() {
		String table = "CREATE TABLE IF NOT EXISTS VIDEO"
				+ "(id_video INTEGER,"
				+ "title_video VARCHAR(140),"
				+ "length NUMBER(5),"
				+ "thumbnail VARCHAR(100),"
				+ "tag VARCHAR(500),"
				+ "status VARCHAR(20) DEFAULT 'public',"
				+ "date_upload_video VARCHAR(10),"
				+ "video_count INTEGER,"
				+ "nb_like INTEGER,"
				+ "id_user INTEGER,"
				+ "PRIMARY KEY (id_video),"
				+ "FOREIGN KEY (id_user) REFERENCES USER (id_user))";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table VIDEO created successfully");
	}
	
	private void createTableComment() {
		String table = "CREATE TABLE IF NOT EXISTS COMMENT"
						+ "(id_comment NUMBER(25),"
						+ "texte_comment VARCHAR(140),"
						+ "date_comment DATE,"
						+ "nb_like INTEGER,"
						+ "id_comment_1 NUMBER(25),"
						+ "id_user NUMBER(25),"
						+ "PRIMARY KEY (id_comment),"
						+ "FOREIGN KEY (id_comment_1) REFERENCES COMMENT (id_comment),"
						+ "FOREIGN KEY (id_user) REFERENCES USER (id_user))";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table COMMENT created successfully");
	}
	
	private void createTableCollection() {
		String table = "CREATE TABLE IF NOT EXISTS COLLECTION"
						 + "(id_collection INTEGER,"
						 + "name_collection VARCHAR(140),"
						 + "id_user INTEGER,"
						 + "FOREIGN KEY (id_user) REFERENCES USER (id_user),"
						 + "PRIMARY KEY (id_collection))";	
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table COLLECCTION created successfully");
	}
	
	private void createTableCompositionCollection() {
		String table = "CREATE TABLE IF NOT EXISTS COMPOSITION_COLLECTION"
						+ "(id_video INTEGER,"
						+ "id_collection INTEGER,"
						+ "PRIMARY KEY (id_video, id_collection),"
						+ "FOREIGN KEY (id_video) REFERENCES TWEET (id_video),"
						+ "FOREIGN KEY (id_collection) REFERENCES COLLECTION (id_collection))";
	
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table COMPOSITION_COLLECTION created successfully");
	}
	
	
	public void deleteTableUser() {
		String table = "DROP TABLE IF EXISTS USER";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table USER deleted successfully");
	}
	
	public void deleteTableSubscribe() {
		String table = "DROP TABLE IF EXISTS SUBSCRIBE";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table SUBSCRIBE deleted successfully");
	}
	
	public void deleteTableVideo() {
		String table = "DROP TABLE IF EXISTS VIDEO";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table VIDEO deleted successfully");
	}
	
	public void deleteTableComment() {
		String table = "DROP TABLE IF EXISTS COMMENT";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table COMMENT deleted successfully");
	}
	
	public void deleteTableCollection() {
		String table = "DROP TABLE IF EXISTS COLLECTION";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table COLLECTION deleted successfully");
	}
	
	public void deleteTableCompositionCollection() {
		String table = "DROP TABLE IF EXISTS COMPOSITION_COLLECTION";
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(table);
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("   Table COMPOSITION_COLLECTION deleted successfully");
	}
	
	public void deleteTable() {
		deleteTableUser();
		deleteTableSubscribe();
		deleteTableVideo();
		deleteTableComment();
		deleteTableCollection();
		deleteTableCompositionCollection();	
	}
}