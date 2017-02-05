package model;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Playlist extends Observable{

	private ArrayList<VideoDescription> insideVideos;
	private String playlistName;
	private String owner;
	private JLabel titre;
	private JPanel videos;
	private Profile playlist;
	
	public Playlist(String name){
		insideVideos = new ArrayList<VideoDescription>();
		playlistName = name;
		titre = new JLabel(name);
		videos = new JPanel();
	}
	
	public Profile getPlaylist() {
		return playlist;
	}

	public JLabel getTitre() {
		return titre;
	}

	public void setTitre(JLabel titre) {
		this.titre = titre;
	}

	public JPanel getVideos() {
		return videos;
	}

	public void setVideos(JPanel videos) {
		this.videos = videos;
	}

	public void addVideoInPlaylist(VideoDescription v){
		insideVideos.add(v);
	}
	
	public void deleteVideoInPlaylist(VideoDescription v){
		for (VideoDescription i : insideVideos){
			if (i.equals(v)){
				insideVideos.remove(v);
			}
		}
	}
	
	public ArrayList<VideoDescription> getInsideVideos(){
		return insideVideos;
	}
	
	public String getPlaylistName(){
		return playlistName;
	}
	
	public void setPlaylistName(String newName){
		playlistName = newName;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public void setOwner(String name){
		owner = name;
	}
	
}
