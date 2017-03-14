package model;

import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JButton;

import view.MainScreen;

/**
 * Menu
 * 
 * @author Claire
 *
 */
public class Menu extends Observable {

	private LinkedList<JButton> menuButtons;
	private MainScreen screen;

	public Menu(MainScreen screen) {
		/*menuButtons = new LinkedList<JButton>();
		this.screen = screen;

		JButton nvelleRegle = new JButton();
		nvelleRegle.setText("Nouvelle regle");
		menuButtons.add(nvelleRegle);

		JButton toeic = new JButton();
		toeic.setText("TOEIC");
		menuButtons.add(toeic);

		JButton phrases = new JButton();
		phrases.setText("Phrases");
		menuButtons.add(phrases);*/
	}

	public LinkedList<JButton> getMenuButtons() {
		return menuButtons;
	}

	public MainScreen getScreen() {
		return screen;
	}

	public void switchScreen(String arg) {
		setChanged();
		//screen.update(this, arg);
		notifyObservers();
	}

	/*public void setDeco() {
		screen.setStateConnection(false);
	}*/
}
