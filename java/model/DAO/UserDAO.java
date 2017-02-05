package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.object.UserModel;

public class UserDAO {

	private Connection connect;

	public UserDAO(Connection c) {
		connect = c;
		UserModel.setId(getNumberOfUser()+1);
	}
	
	public void create(UserModel u){
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO USER (id_user,username,mail,password, id_comment, id_collection)" 
								+ "VALUES (?,?,?,?,?,?)");
			prepare.setInt(1, u.getIdUser());
			prepare.setString(2, u.getUsername());
			prepare.setString(3, u.getMail());
			prepare.setString(4, u.getPassword());
			prepare.setInt(5, u.getId_comment());
			prepare.setInt(6, u.getId_collection());
			prepare.executeUpdate();
			prepare.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean exist(UserModel u) {
		boolean result = true;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM USER WHERE id_user = " + u.getIdUser());
			result = res.next();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void update(UserModel u){
		try {
			if (exist(u)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE USER SET"
								+" username = '"+ u.getUsername()
								+"', mail = '"+ u.getMail()
								+"', password = '"+ u.getPassword()
								+"',id_comment = "+ u.getId_comment()
								+",id_collection "+ u.getId_collection()
								+" WHERE id_user = " + u.getIdUser());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(UserModel u){
		try {
			if (exist(u)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("DELETE FROM USER"
								+" WHERE id_user = " + u.getIdUser());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updateTableUser(u.getIdUser());
	}
	
	private void updateTableUser(int idUDelete){
		int numberOfUser = getNumberOfUser();
		UserModel.setId(numberOfUser+1);
		for (int i = idUDelete; i < numberOfUser+1 ;i++) {
			try {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE USER SET"
								+" id_user = "+ i
								+" WHERE id_user = " + (i+1));
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
					.executeQuery("SELECT count(*) FROM USER");
			result = res.getInt(1);
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public UserModel find(int i){
		UserModel u = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM USER WHERE id_user = " + i);
			if (result.next()) {
				String username = result.getString("username");
				String mail = result.getString("mail");
				String password = result.getString("password");
				int id_comment = result.getInt("id_comment");
				int id_collection = result.getInt("id_collection");
				
				u = new UserModel(i,username,mail,password, id_comment, id_collection);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
}