package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.object.CompositionCollectionModel;

public class CompositionCollectionDAO {

	private Connection connect;

	public CompositionCollectionDAO(Connection c) {
		connect = c;
		CompositionCollectionModel.setId(getNumberOfUser()+1);
	}
	
	public void create(CompositionCollectionModel u){
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO COMPOSITION_COLLECTION (id_video,id_collection)" 
								+ "VALUES (?,?)");
			prepare.setInt(1, u.getIdVideo());
			prepare.setInt(2, u.getIdCollection());
			prepare.executeUpdate();
			prepare.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean exist(CompositionCollectionModel u) {
		boolean result = true;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COMPOSITION_COLLECTION WHERE id_video = " + u.getIdVideo());
			result = res.next();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void update(CompositionCollectionModel u){
		try {
			if (exist(u)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE COMPOSITION_COLLECTION SET"
								+" id_collection = '"+ u.getIdCollection()
								+" WHERE id_ = " + u.getIdVideo());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(CompositionCollectionModel u){
		try {
			if (exist(u)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("DELETE FROM COMPOSITION_COLLECTION"
								+" WHERE id_video = " + u.getIdVideo());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updateTableUser(u.getIdVideo());
	}
	
	private void updateTableUser(int idUDelete){
		int numberOfUser = getNumberOfUser();
		CompositionCollectionModel.setId(numberOfUser+1);
		for (int i = idUDelete; i < numberOfUser+1 ;i++) {
			try {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE COMPOSITION_COLLECTION SET"
								+" id_video = "+ i
								+" WHERE id_video = " + (i+1));
				prepare.executeUpdate();
				prepare.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

	
	private int getNumberOfUser() {
		int result = 0;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT count(*) FROM COMPOSITION_COLLECTION");
			result = res.getInt(1);
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public CompositionCollectionModel find(int i){
		CompositionCollectionModel u = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COMPOSITION_COLLECTION WHERE id_video = " + i);
			if (result.next()) {
				int collection = result.getInt("id_collection");
				u = new CompositionCollectionModel(i,collection);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
}