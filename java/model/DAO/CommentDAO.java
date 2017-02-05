package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.object.CommentModel;

public class CommentDAO {

	
	private Connection connect;

	public CommentDAO(Connection c) {
		connect = c;
		CommentModel.setId(getNumberOfComment() + 1);
	}

	public void create(CommentModel com) {
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO COMMENT (id_comment, texte_comment, date_comment, nb_like, id_comment_1, id_user)"
									+ "VALUES (?,?,?,?,?,?)");
			prepare.setInt(1, com.getIdComment());
			prepare.setString(2, com.getTextComment());
			prepare.setString(3, com.getDateComment());
			prepare.setInt(4, com.getNbLike());
			prepare.setInt(5, com.getIdComment1());;
			prepare.setInt(6, com.getIdUser());
			prepare.executeUpdate();
			prepare.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean exist(CommentModel com) {
		boolean result = true;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COMMENT WHERE id_comment = " + com.getIdComment());
			result = res.next();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void update(CommentModel com) {
		try {
			if (exist(com)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE COMMENT SET" 
								+ " id_comment = " + CommentModel.getId()
								+ ", texte_comment = '" + com.getTextComment() 
								+ "', date_comment = '" + com.getDateComment() 
								+ "', nb_like = " + com.getNbLike() 
								+ ", id_comment_1 = " + com.getIdComment1() 
								+ ", id_user = " + com.getIdUser()
								+ " WHERE id_comment = " +com.getIdComment());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void delete(CommentModel com) {
		try {
			if (exist(com)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("DELETE FROM COMMENT" 
								+" WHERE id_comment = " + com.getIdComment());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updateTableComment(com.getIdComment());
	}

	private void updateTableComment(int idComDelete) {
		int numberOfComment = getNumberOfComment();
		CommentModel.setId(numberOfComment + 1);
		for (int i = idComDelete; i < numberOfComment + 1; i++) {
			try {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE COMMENT SET" 
								+ " id_comment = " + i 
								+ " WHERE id_comment = " + (i + 1));
				prepare.executeUpdate();
				prepare.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}




	private int getNumberOfComment() {
		int result = 0;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT count(*) FROM COMMENT");
			result = res.getInt(1);
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public CommentModel find(int i) {
		CommentModel com = null;
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COMMENT WHERE id_comment = " + i);
			if (result.next()) {
				String txtcom = result.getString("texte_comment");
				String date = result.getString("date_comment");
				int nblike = result.getInt("nb_like");
				int com1 = result.getInt("id_comment_1");
				int idU = result.getInt("id_user");
				com = new CommentModel(i,txtcom, date, nblike, com1, idU);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return com;
	}
}
