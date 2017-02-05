package model.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DAO.CollectionDAO;
import model.DAO.CommentDAO;
import model.DAO.CompositionCollectionDAO;
import model.DAO.UserDAO;
import model.DAO.VideoDAO;
import model.object.CollectionModel;
import model.object.CommentModel;
import model.object.CompositionCollectionModel;
import model.object.UserModel;
import model.object.VideoModel;


public class Request {
	
	private Connection connect;
	private UserDAO sDAO;
	private CommentDAO cDAO;
	private VideoDAO vDAO;
	private CollectionDAO colDAO;
	private CompositionCollectionDAO compDAO;

	public Request(Connection c) {
		connect = c;
		sDAO = new UserDAO(c);
		cDAO = new CommentDAO(c);
		vDAO = new VideoDAO(c);
		colDAO = new CollectionDAO(c);
		compDAO = new CompositionCollectionDAO(c);
		
	}
	/*	public ArrayList<Question> getAllQuestions() {
		ArrayList<Question> questionList = new ArrayList<Question>();
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM QUESTIONS");
			while (res.next()) {
				questionList.add(qDAO.find(res.getInt("id")));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questionList;
	}
	
	public ArrayList<Question> getAllQuestionsByAuthor(String author) {
		ArrayList<Question> questionList = new ArrayList<Question>();
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM QUESTIONS WHERE author = '"+author+"'");
			while (res.next()) {
				questionList.add(qDAO.find(res.getInt("id")));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questionList;
	}
	
	public ArrayList<Question> getAllQuestionsByDifficulty(int diff, String s) {
		ArrayList<Question> questionList = new ArrayList<Question>();
		if (!s.equals("admin")) {
			try {
				ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT * FROM QUESTIONS WHERE difficulty = "+diff+ " AND author != '"+s+"'");
				while (res.next()) {
					questionList.add(qDAO.find(res.getInt("id")));
				}
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT * FROM QUESTIONS WHERE difficulty = "+diff);
				while (res.next()) {
					questionList.add(qDAO.find(res.getInt("id")));
				}
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return questionList;
	}*/
	
	public ArrayList<UserModel> getAllUser() {
		ArrayList<UserModel> uList = new ArrayList<UserModel>();
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM USER");
			while (res.next()) {
				uList.add(sDAO.find(res.getInt("id_user")));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}	
	
	public ArrayList<CommentModel> getAllComment() {
		ArrayList<CommentModel> uList = new ArrayList<CommentModel>();
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COMMENT");
			while (res.next()) {
				uList.add(cDAO.find(res.getInt("id_comment")));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}	
	
	
	public ArrayList<VideoModel> getAllVideo() {
		ArrayList<VideoModel> uList = new ArrayList<VideoModel>();
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM VIDEO");
			while (res.next()) {
				uList.add(vDAO.find(res.getInt("id_video")));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}	
	
	public ArrayList<CollectionModel> getAllCollection() {
		ArrayList<CollectionModel> uList = new ArrayList<CollectionModel>();
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COLLECTION");
			while (res.next()) {
				uList.add(colDAO.find(res.getInt("id_collection")));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}	
	
	public ArrayList<CompositionCollectionModel> getAllCompositionCollection() {
		ArrayList<CompositionCollectionModel> uList = new ArrayList<CompositionCollectionModel>();
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COMPOSITION_COLLECTION");
			while (res.next()) {
				uList.add(compDAO.find(res.getInt("id_video")));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}
	
	public ArrayList<CollectionModel> getAllCollectionByUser(UserModel u) {
		ArrayList<CollectionModel> uList = new ArrayList<CollectionModel>();
		try {
			
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM COLLECTION WHERE id_user = "+ u.getIdUser());
			while (res.next()) {
				uList.add(colDAO.find(res.getInt("id_collection")));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}
}