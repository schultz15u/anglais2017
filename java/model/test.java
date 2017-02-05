package model;


import java.sql.SQLException;
//import java.util.ArrayList;
//import model.DataBase.ViewDataBase;
//import model.object.CollectionModel;
//import model.object.CommentModel;
//import model.object.CompositionCollectionModel;
//import model.object.UserModel;
//import model.object.VideoModel;


public class test {
	@SuppressWarnings("unused")
	private static DataBaseModel D;
	/*private static ViewDataBase V;
	private static ArrayList<UserModel> uliste;
	private static ArrayList<CommentModel> cliste;
	private static UserModel u ;
	private static CommentModel c;
	private static VideoModel v;
	private static ArrayList<VideoModel> vliste;
	private static ArrayList<CollectionModel> colliste;
	private static CollectionModel co;
	private static CompositionCollectionModel comp;
	private static ArrayList<CompositionCollectionModel> compliste;*/
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		
		D = new DataBaseModel();
		//u = new UserModel(1,"grr", "mamiSchultz@mamie.com", "jaimelalsace", 0, 0);
		//D.addUser(new UserModel("grejd", "mamiSchultvhcsmamie.com", "jaimelalsace", 0, 0));
		//D.addUser(new UserModel("grjbdsb", "mamiSchultvhcsmamie.com", "jaimelalsace", 0, 0));
		//D.deleteUser(new UserModel("name", "adresse", "pass"));
		//v = new VideoModel(null, 0, null, null, null, null, 0, 0, 0);
		
		//D.addVideo(v);
		//D.addCollection(new CollectionModel("hh", 1));
		//v = new VideoModel("fnjnksvbhvs", null, null, null, null, null, 0, 0, 0, 1);
		//c = new CommentModel("bjsdf" ,"g", 0, 0, 0);
		//D.deleteComment(c);
		//comp = new CompositionCollectionModel(0);
		//D.addCompositionCollection(comp);
		//compliste = new ArrayList<CompositionCollectionModel>();
		
		/*colliste = new ArrayList<CollectionModel>();
		vliste = new ArrayList<VideoModel>();
		cliste = new ArrayList<CommentModel>();
		uliste = new ArrayList<UserModel>();
		cliste = D.getAllComment();
		vliste = D.getAllVideo();
		uliste = D.getAllUser();
		colliste = D.getAllCollection();
		compliste = D.getAllCompositionCollection();
		//u = new UserModel(4, "patrick", "mamiSchultz@mamie.com", "jaimelalsace");
		//D.deleteUser(u);
		V.ViewUser(uliste);
		V.ViewCollection(colliste);
		//V.ViewComment(cliste);
		//V.ViewVideo(vliste);
		//V.ViewCompositionCollection(compliste);
		
		D.close();*/
	}

}
