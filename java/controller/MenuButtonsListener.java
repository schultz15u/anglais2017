package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Menu;

public class MenuButtonsListener implements ActionListener {

	private Menu menuReference;

	public MenuButtonsListener(Menu menu) {
		menuReference = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (menuReference.getMenuButtons().get(0).equals(e.getSource())) {
			menuReference.switchScreen(menuReference.getMenuButtons().get(0).getText());
		}
		if (menuReference.getMenuButtons().get(1).equals(e.getSource())) {
			menuReference.switchScreen(menuReference.getMenuButtons().get(1).getText());
		}
		if (menuReference.getMenuButtons().get(2).equals(e.getSource())) {
			menuReference.switchScreen(menuReference.getMenuButtons().get(2).getText());
		}
		/*
		 * if (menuReference.getMenuButtons().get(3).equals(e.getSource())) {
		 * menuReference.switchScreen(menuReference.getMenuButtons().get(3).
		 * getText()); }
		 */

		// if (menuReference.getMenuButtons().get(5).equals(e.getSource())) {
		// menuReference.setDeco();
		// }

	}

}
