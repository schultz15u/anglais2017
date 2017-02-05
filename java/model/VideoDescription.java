package model;

import java.util.Observable;

/**
 * Caracteristics of a video
 * @author Menou
 *
 */

//model
public class VideoDescription extends Observable {

	private String videoName;
	private String videoId;
	private String thumbnail;
	private String date;
	private String description;
	
	public VideoDescription(String title, String id, String url, String description){
		videoName = title;
		videoId = id;
		thumbnail = url;
		this.description = description;
	}
	
	public String getvideoName() {
		return videoName;
	}
	
	public String getvideoId() {
		return videoId;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void afficheVideo() {
		setChanged();
		notifyObservers();
	}
	
}
