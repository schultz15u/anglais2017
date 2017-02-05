package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainScreen;

public class LogoButtonListener implements ActionListener {
	private MainScreen screen;
	
	public LogoButtonListener(MainScreen screen) {
		this.screen = screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		screen.getCl().show(screen.getContent(), screen.getListContent()[screen.getListContent().length -1] );
	}
}
