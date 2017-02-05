package model;

import java.sql.Connection;
import java.util.ArrayList;

import model.object.CollectionModel;
import model.object.CommentModel;
import model.object.CompositionCollectionModel;
//import tous les objets
import model.object.UserModel;
import model.object.VideoModel;
import model.DAO.UserDAO;
import model.DAO.VideoDAO;
import model.DAO.CollectionDAO;
import model.DAO.CommentDAO;
import model.DAO.CompositionCollectionDAO;
import model.DataBase.ConnectionManager;
import model.DataBase.Request;

public class DataBaseModel {
	
	private ConnectionManager CM;
	private UserDAO uDAO;
	private Request request;
	private CommentDAO cDAO;
	private VideoDAO vDAO;
	private CollectionDAO colDAO;
	private CompositionCollectionDAO compDAO;
	
	public DataBaseModel() {
		CM = new ConnectionManager();
		Connection c = CM.getConnection();
		uDAO = new UserDAO(c);
		cDAO = new CommentDAO(c);
		request = new Request(c);
		vDAO = new VideoDAO(c);
		colDAO = new CollectionDAO(c);
		compDAO = new CompositionCollectionDAO(c);
	}
	
	public void close(){
		CM.disconnect();
	}

	public void addUser(UserModel u) {
		if (uDAO.exist(u)) {
			System.out.println("plop");
			uDAO.update(u);
		} else {
			System.out.println("crée moi");
			uDAO.create(u);
		}
	}
	
	public void deleteUser(UserModel u) {
		uDAO.delete(u);
	}
	
	public UserModel findUser(int i){
		return uDAO.find(i);
	}
	
	public ArrayList<UserModel> getAllUser() {
		return request.getAllUser();
	}
	
	public ArrayList<CollectionModel> getAllCollectionByUser(UserModel u) {
		return request.getAllCollectionByUser(u);
	}
	
	public void addComment(CommentModel com) {
		if (cDAO.exist(com)) {
			cDAO.update(com);
		} else {
			cDAO.create(com);
		}
	}
	
	public void deleteComment(CommentModel com) {
		cDAO.delete(com);
	}
	
	public CommentModel findComment(int i){
		return cDAO.find(i);
	}
	
	public ArrayList<CommentModel> getAllComment() {
		return request.getAllComment();
	}
	
	public void addVideo(VideoModel v) {
		if (vDAO.exist(v)) {
			vDAO.update(v);
		} else {
			vDAO.create(v);
		}
	}
	
	public void deleteVideo(VideoModel v) {
		vDAO.delete(v);
	}
	
	public VideoModel findVideo(int i){
		return vDAO.find(i);
	}
	
	public ArrayList<VideoModel> getAllVideo() {
		return request.getAllVideo();
	}
	
	public void addCollection(CollectionModel v) {
		if (colDAO.exist(v)) {
			colDAO.update(v);
		} else {
			colDAO.create(v);
		}
	}
	
	public void deleteCollection(CollectionModel v) {
		colDAO.delete(v);
	}
	
	public CollectionModel findCollection(int i){
		return colDAO.find(i);
	}
	
	public ArrayList<CollectionModel> getAllCollection() {
		return request.getAllCollection();
	}
	
	
	public void addCompositionCollection(CompositionCollectionModel v) {
		if (compDAO.exist(v)) {
			compDAO.update(v);
		} else {
			compDAO.create(v);
		}
	}
	
	public void deleteCompositionCollection(CompositionCollectionModel v) {
		compDAO.delete(v);
	}
	
	public CompositionCollectionModel findCompositionCollection(int i){
		return compDAO.find(i);
	}
	
	public ArrayList<CompositionCollectionModel> getAllCompositionCollection() {
		return request.getAllCompositionCollection();
	}
}