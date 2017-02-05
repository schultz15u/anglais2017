package model;

import java.util.ArrayList;
import java.util.Observable;

public class Profile extends Observable {
	
	private String userName;
	private ArrayList<Playlist> allPlaylists;
	private Playlist subscribements;
	private Playlist favorites;
	private Playlist uploaded;
	private String pathProfilePic;
	/*path=directory.getCanonicalPath()+"/resources/images/logo1.png";
               img = ImageIO.read(new File(getDefaultImageUploadPath());*/
	
	public Profile(String userName){
		this.userName = userName;
		allPlaylists = new ArrayList<Playlist>();
		subscribements = new Playlist("subscribements");
		favorites = new Playlist("favorites");
		uploaded = new Playlist("uploaded");
		subscribements.setOwner(userName);
		favorites.setOwner(userName);
		uploaded.setOwner(userName);
		allPlaylists.add(favorites);
		allPlaylists.add(uploaded);
		allPlaylists.add(subscribements);
	}
	
	public Profile(){
		allPlaylists = new ArrayList<Playlist>();
		subscribements = new Playlist("Abonnements");
		favorites = new Playlist("Favoris");
		uploaded = new Playlist("Mes vidéos");
		allPlaylists.add(favorites);
		allPlaylists.add(uploaded);
		allPlaylists.add(subscribements);
	}
	
	public void chooseANewProfilePic(String path){
		pathProfilePic = path;
	}
	
	public String getProfilePicPath(){
		return pathProfilePic;
	}
	
	public void createPlaylist(String name){
		allPlaylists.add(new Playlist(name));
		setChanged();
		notifyObservers();
	}

	public void deletePlaylist(Playlist p){
		for (Playlist i : allPlaylists){
			if (i.equals(p)){
				allPlaylists.remove(p);
			}
		}
	}
	
	public void changePlaylistName(Playlist p, String name){
		p.setPlaylistName(name);
	}
	
	public void addVideoInPlaylist(Playlist p, VideoDescription v){
		p.addVideoInPlaylist(v);
	}
	
	public void deleteVideoInPlaylist(Playlist p, VideoDescription v){
		p.deleteVideoInPlaylist(v);
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Playlist> getAllPlaylists() {
		return allPlaylists;
	}

	public void setAllPlaylists(ArrayList<Playlist> allPlaylists) {
		this.allPlaylists = allPlaylists;
	}

	public Playlist getSubscribements() {
		return subscribements;
	}

	public void setSubscribements(Playlist subscribements) {
		this.subscribements = subscribements;
	}

	public Playlist getFavorites() {
		return favorites;
	}

	public void setFavorites(Playlist favorites) {
		this.favorites = favorites;
	}

	public Playlist getUploaded() {
		return uploaded;
	}

	public void setUploaded(Playlist uploaded) {
		this.uploaded = uploaded;
	}
	
	public void getPlaylist(Playlist p){
		for (Playlist i : allPlaylists){
			if (p.equals(i)){
				deletePlaylist(p);
			}
		}
	}
	
}
