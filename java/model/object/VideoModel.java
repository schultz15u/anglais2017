package model.object;

import java.util.Observable;

public class VideoModel extends Observable {
	
	private static int id = 1;
	
	public static void setId(int id){
		VideoModel.id = id;
	}
	
	public static int getId(){
		return id;
	}

	private int idVideo;
	private String title_video;
	private int length;
	private String thumbnail;
	private String tag;
	private String status;
	private String date_upload_video;
	private int video_count;
	private int nb_like;
	private int id_user;
	
	public VideoModel(String title, int length1, String t, String tag1, String status1, String date, int videoCount, int likes, int idU){
		idVideo = id;
		title_video = title;
		length = length1;
		thumbnail = t;
		tag = tag1;
		status = status1;
		date_upload_video = date;
		video_count = videoCount;
		nb_like = likes;
		id_user = idU;
		id++;
	}
	
	public VideoModel(int id, String title, int length1, String t, String tag1, String status1, String date, int videoCount, int likes, int idU){
		idVideo = id;
		title_video = title;
		length = length1;
		thumbnail = t;
		tag = tag1;
		status = status1;
		date_upload_video = date;
		video_count = videoCount;
		nb_like = likes;
		id_user = idU;
	}
	
	
	public String getTitleVideo(){
		return title_video;
	}
	
	public String getThumbnail(){
		return thumbnail;
	}
	
	public String getTag(){
		return tag;
	}
	
	public String getStatus(){
		return status;
	}
	
	public String getDate(){
		return date_upload_video;
	}
	
	public void setTitleVideo(String title){
		title_video = title;
	}
	
	public void setThumbnail(String thumb){
		thumbnail = thumb;
	}
	
	public void setTag(String t){
		tag = t;
	}
	
	public void setStatus(String s){
		status = s;
	}
	
	public void setDate(String d){
		date_upload_video = d;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getVideoCount() {
		return video_count;
	}

	public void setVideoCount(int videoCount) {
		this.video_count = videoCount;
	}

	public int getNbLike() {
		return nb_like;
	}

	public void setNbLike(int likesNumber) {
		this.nb_like = likesNumber;
	}

	public int getIdUser() {
		return id_user;
	}

	public void setIdUser(int idUser) {
		this.id_user = idUser;
	}

	public int getIdVideo() {
		return idVideo;
	}

	
}
