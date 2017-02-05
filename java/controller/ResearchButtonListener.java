package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ResearchBar;

public class ResearchButtonListener implements ActionListener {

	private ResearchBar barReference;
	
	public ResearchButtonListener(ResearchBar bar) {
		barReference = bar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		barReference.generateResults();
	}
}
