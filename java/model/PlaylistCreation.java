package model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import model.object.CollectionModel;
import view.MainScreen;

public class PlaylistCreation extends Observable {

	private JFormattedTextField newPlaylistName;
	private JLabel label;
	private JButton b;
	private MainScreen screen;
	
	public PlaylistCreation(MainScreen screen){
		//this.user = screen.getC();
		newPlaylistName = new JFormattedTextField();
		label = new JLabel("Nouvelle playlist");
		b = new JButton ("Créer");
		this.setScreen(screen);
		this.addObserver(screen);
		
		newPlaylistName.setPreferredSize(new Dimension(150, 30));
	}

	public JFormattedTextField getResearchBar() {
		return newPlaylistName;
	}

	public JLabel getLabel() {
		return label;
	}

	public JButton getB() {
		return b;
	}

	public MainScreen getScreen() {
		return screen;
	}

	public void setScreen(MainScreen screen) {
		this.screen = screen;
	}

	public JFormattedTextField getNewPlaylistName() {
		return newPlaylistName;
	}

	public void setNewPlaylistName(JFormattedTextField newPlaylistName) {
		this.newPlaylistName = newPlaylistName;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public void setB(JButton b) {
		this.b = b;
	}

	public void createPlaylist(String text) {
		setChanged();
		//user.createPlaylist(text);
		//screen.getConnectedUser().set
		notifyObservers();
	}

	public ArrayList<CollectionModel> getAllPlaylists() {
		return screen.getMyPlaylists();
	}
}
