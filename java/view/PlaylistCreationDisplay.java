package view;

import javax.swing.JPanel;

import controller.PlaylistCreationListener;
import model.PlaylistCreation;

@SuppressWarnings("serial")
public class PlaylistCreationDisplay extends JPanel{

	private PlaylistCreation barReference;
	
	public PlaylistCreationDisplay(PlaylistCreation bar){
		barReference = bar;
		add(barReference.getLabel());
		add(barReference.getResearchBar());
		barReference.getB().addActionListener(new PlaylistCreationListener(barReference));
		//barReference.getResearchBar().addKeyListener(new ResearchBarListener(barReference));
		add(barReference.getB());
	}
	
	public PlaylistCreation getBarModel(){
		return barReference;
	}
	
}
