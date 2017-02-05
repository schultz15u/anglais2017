package model.DataBase;


import java.util.ArrayList;

import model.object.CollectionModel;
import model.object.CommentModel;
import model.object.CompositionCollectionModel;
import model.object.UserModel;
import model.object.VideoModel;

public class ViewDataBase {

	public static void ViewUser(ArrayList<UserModel> uList) {
		System.out.println("User Table :");
		String leftAlignFormat = "| %-4d | %-17s |  %-25s |  %-13s | %-4d | %-4d |%n";
		System.out.format("+------+-------------------+----------------------------+----------------+%n");
		System.out.format("|  ID  |      Username     |          Mail              |    Password    |%n");
		System.out.format("+------+-------------------+----------------------------+----------------+%n");
		for (UserModel u: uList) {
			int id_user = u.getIdUser();
			String username = u.getUsername();
			String mail = u.getMail();
			String password = u.getPassword();
			int id_comment = u.getId_comment();
			int id_collection = u.getId_collection();
			System.out.format(leftAlignFormat, id_user, username, mail, password, id_comment, id_collection);
		}
		System.out.format("+------+-------------------+----------------------------+----------------+%n");

	}
	
	public static void ViewCollection(ArrayList<CollectionModel> uList) {
		System.out.println("User Table :");
		String leftAlignFormat = "| %-4d | %-4s | %-4d | %n";
		System.out.format("+------+-------------------+%n");
		System.out.format("|  ID  |      Username     |%n");
		System.out.format("+------+-------------------+%n");
		for (CollectionModel u: uList) {
			int id_collection = u.getIdCollection();
			String name_collection = u.getNameCollection();
			int id_user = u.getId_user();
			System.out.format(leftAlignFormat, id_collection,name_collection,id_user);
		}
		System.out.format("+------+-------------------+%n");

	}
	
	public static void ViewComment(ArrayList<CommentModel> cList) {
		System.out.println("Comment Table :");
		String leftAlignFormat = "| %-4d | %-17s |  %-25s |  %-13s | %-4d |%n";
		System.out.format("+------+-------------------+----------------------------+----------------+------+%n");
		System.out.format("|  ID  |      texte        |          date              |    IDComment1  |  ID  |%n");
		System.out.format("+------+-------------------+----------------------------+----------------+------+%n");
		for (CommentModel c: cList) {
			int id_comment = c.getIdComment();
			String texte_comment = c.getTextComment();
			String date_comment = c.getDateComment();
			int id_comment_1 = c.getIdComment1();
			int id_user = c.getIdUser();
			System.out.format(leftAlignFormat, id_comment, texte_comment, date_comment, id_comment_1, id_user );
		}
		System.out.format("+------+-------------------+----------------------------+----------------+%n");

	}
	
	public static void ViewVideo(ArrayList<VideoModel> vList) {
		System.out.println("Video Table :");
		String leftAlignFormat = "| %-4d | %-10s |  %-25d |  %-25s | %-10s | %-10s | %-10s |  %-25d |  %-13d | %-4d |%n";
		System.out.format("+------+-------------------+----------------------+---------+---------+------+---------+---------+-----------+---------+%n");
		System.out.format("|  ID  |      titre        |      thumbnail       |    tag  |  status | date |  length | nb_like | nb de vue | ID user |%n");
		System.out.format("+------+-------------------+----------------------+---------+---------+------+---------+---------+-----------+---------+%n");
		for (VideoModel v: vList) {
			int id_video = v.getIdVideo();
			String title_video = v.getTitleVideo();
			int length = v.getLength();
			String thumbnail = v.getThumbnail();
			String tag = v.getTag();
			String status = v.getStatus();
			String date_upload_video = v.getDate();
			int video_count = v.getVideoCount();
			int nb_like = v.getNbLike();
			int id_user = v.getIdUser();
			
			System.out.format(leftAlignFormat,id_video,title_video,length,thumbnail,tag,status,date_upload_video,video_count,nb_like,id_user);
		}
		System.out.format("+------+-------------------+----------------------+---------+---------+------+---------+---------+-----------+---------+%n");

	}
	
	public static void ViewCompositionCollection(ArrayList<CompositionCollectionModel> uList) {
		System.out.println("Comp Table :");
		String leftAlignFormat = "| %-4d | %-4d | %n";
		System.out.format("+------+-------------------+%n");
		System.out.format("|  ID  |      collection     |%n");
		System.out.format("+------+-------------------+%n");
		for (CompositionCollectionModel u: uList) {
			int id_collection = u.getIdVideo();
			int name_collection = u.getIdCollection();
			System.out.format(leftAlignFormat, id_collection,name_collection);
		}
		System.out.format("+------+-------------------+%n");

	}
	
}