package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.object.SubscribeModel;

public class SubscribeDAO {

	private Connection connect;

	public SubscribeDAO(Connection c) {
		connect = c;
		SubscribeModel.setIdUser(getNumberOfSubscribe() + 1);
	}

	public void create(SubscribeModel s) {
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO SUBSCRIBE (idUser, idUser1)" + "VALUES (?,?)");
			prepare.setInt(1, s.getIdUser());
			prepare.setInt(2, s.getIdUser1());
			prepare.executeUpdate();
			prepare.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(SubscribeModel s) {
		try {
			if (exist(s)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("DELETE FROM SUBSCRIBE" + " WHERE id = " + s.getIdUser());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updateTableSubscribe(s.getIdUser());
	}

	private void updateTableSubscribe(int idSDelete) {
		int numberOfSubscribe = getNumberOfSubscribe();
		SubscribeModel.setIdUser(numberOfSubscribe + 1);
		for (int i = idSDelete; i < numberOfSubscribe + 1; i++) {
			try {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE SUBSCRIBE SET" + " id = " + i + " WHERE id = " + (i + 1));
				prepare.executeUpdate();
				prepare.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean exist(SubscribeModel s) {
		boolean result = true;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM SUBSCRIBE WHERE id = " + s.getIdUser());
			result = res.next();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void update(SubscribeModel s) {
		try {
			if (exist(s)) {
				PreparedStatement prepare = this.connect.prepareStatement(
						"UPDATE SUBSCRIBE SET" + " idUser = '" + s.getIdUser() + "', idUser1 = '" + s.getIdUser1());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getNumberOfSubscribe() {
		int result = 0;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT count(*) FROM SUBSCRIBE");
			result = res.getInt(1);
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public SubscribeModel find(int i) {
		SubscribeModel s = null;
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM SUBSCRIBE WHERE id = " + i);
			if (result.next()) {
				int i1 = result.getInt("idUser1");
				s = new SubscribeModel(i, i1);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

}
