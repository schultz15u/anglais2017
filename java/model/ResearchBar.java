package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import view.MainScreen;

public class ResearchBar extends Observable {
	
	private JFormattedTextField researchBar;
	private JLabel label;
	private JButton b;
	private MainScreen screen;
	private ArrayList<VideoDescription> results;
	
	public ResearchBar(MainScreen screen){
		researchBar = new JFormattedTextField();
		label = new JLabel("Recherche");
		label.setForeground(Color.white);
		b = new JButton ("OK");
		this.setScreen(screen);
		
		researchBar.setPreferredSize(new Dimension(300, 30));
	}

	public JFormattedTextField getResearchBar() {
		return researchBar;
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
	
	public void generateResults() {
		results = new ArrayList<VideoDescription>();
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<VideoDescription> getResults(){
		return results;
	}
	
	
}