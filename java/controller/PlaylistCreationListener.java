package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.DataBaseModel;
import model.PlaylistCreation;
import model.DataBase.ViewDataBase;
import model.object.CollectionModel;

public class PlaylistCreationListener implements ActionListener {

	private PlaylistCreation creationPlaylistBarReference;
	private static CollectionModel nouvellePlaylist;
	
	public PlaylistCreationListener(PlaylistCreation c) {
		creationPlaylistBarReference = c;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = creationPlaylistBarReference.getNewPlaylistName().getText().trim();
		if (!(text.equals(null) || text.equals("") || (text.equals("Mes vidéos") || text.equals("Favoris") || (text.length() == 0)))){
			nouvellePlaylist = new CollectionModel(text, creationPlaylistBarReference.getScreen().getConnectedUser().getIdUser());
			DataBaseModel D = new DataBaseModel();
			D.addCollection(nouvellePlaylist);
			D.addCollection(nouvellePlaylist);
			creationPlaylistBarReference.getScreen().getMyPlaylists().add(nouvellePlaylist);
			D.close();

			ViewDataBase V = new ViewDataBase();
			V.ViewCollection(creationPlaylistBarReference.getScreen().getMyPlaylists());
			
			creationPlaylistBarReference.createPlaylist(creationPlaylistBarReference.getNewPlaylistName().getText());
			ArrayList<CollectionModel> playlists = creationPlaylistBarReference.getAllPlaylists();
			int index = playlists.size();
			index--;
			System.out.println(""
					+ "j'ai créé la liste "+playlists.get(index).getNameCollection());
		}
	}

}
