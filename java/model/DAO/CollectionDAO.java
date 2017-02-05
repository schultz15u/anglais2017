package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.object.CollectionModel;

public class CollectionDAO {

	private Connection connect;

	public CollectionDAO(Connection c) {
		connect = c;
		CollectionModel.setId(getNumberOfCollection()+1);
	}
	
	public void create(CollectionModel u){
		try {			
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO COLLECTION (id_collection, name_collection, id_user)" 
									+ "VALUES (?,?,?)");
			prepare.setInt(1, u.getIdCollection());
			prepare.setString(2, u.getNameCollection());
			prepare.setInt(3,u.getId_user());
			prepare.executeUpdate();
			prepare.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean exist(CollectionModel u) {
		boolean result = true;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COLLECTION WHERE id_collection = " + u.getIdCollection());
			result = res.next();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void update(CollectionModel u){
		try {
			if (exist(u)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE COLLECTION SET"
								+" name_collection = '"+ u.getNameCollection()
								+"',id_user = "+ u.getId_user()
								+"' WHERE id_collection = " + u.getIdCollection()
								);
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(CollectionModel u){
		try {
			if (exist(u)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("DELETE FROM COLLECTION"
								+" WHERE id_collection = " + u.getIdCollection());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updateTableCollection(u.getIdCollection());
	}
	
	private void updateTableCollection(int idUDelete){
		int numberOfCollection = getNumberOfCollection();
		CollectionModel.setId(numberOfCollection+1);
		for (int i = idUDelete; i < numberOfCollection+1 ;i++) {
			try {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE COLLECTION SET"
								+" id_collection = "+ i
								+" WHERE id_collection = " + (i+1));
				prepare.executeUpdate();
				prepare.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
		

	private int getNumberOfCollection() {
		int result = 0;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT count(*) FROM COLLECTION");
			result = res.getInt(1);
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public CollectionModel find(int i){
		CollectionModel u = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COLLECTION WHERE id_collection = " + i);
			if (result.next()) {
				@SuppressWarnings("unused")
				int id_collection = result.getInt("id_collection");
				String name_collection = result.getString("name_collection");
				int id_user = result.getInt("id_user");
				u = new CollectionModel(i,name_collection,id_user);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
}