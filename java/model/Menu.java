package model;

import java.util.LinkedList;
import java.util.Observable;
import javax.swing.JButton;
import view.MainScreen;

/**
 * Menu
 * @author Menou
 *
 */
public class Menu extends Observable {

	private LinkedList<JButton> menuButtons;
	private MainScreen screen;
	
	public Menu(MainScreen screen){
		menuButtons = new LinkedList<JButton>();
		this.screen = screen;
		
		JButton subscribes = new JButton();
		subscribes.setText("Abonnements");
		menuButtons.add(subscribes);
		

		JButton playlists = new JButton();
		playlists.setText("Playlists");
		menuButtons.add(playlists);		

		JButton profile = new JButton();
		profile.setText("Profil");
		menuButtons.add(profile);
		

		JButton connect = new JButton();
		connect.setText("Authentification");
		menuButtons.add(connect);
		
		JButton inscription = new JButton();
		inscription.setText("Inscription");
		menuButtons.add(inscription);
		
		JButton deconnection = new JButton();
		deconnection.setText("Déconnexion");
		menuButtons.add(deconnection);
	}
	
	public LinkedList<JButton> getMenuButtons(){
		return menuButtons;
	}
	
	public MainScreen getScreen(){
		return screen;
	}
	
	public void switchScreen(String arg){
		setChanged();
		screen.update(this, arg);
		notifyObservers();
	}

	public void setDeco() {
		screen.setStateConnection(false);
	}	
}
