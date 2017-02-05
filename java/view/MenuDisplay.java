package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.MenuButtonsListener;
import model.Menu;

@SuppressWarnings("serial")
public class MenuDisplay extends JPanel implements Observer {

	private Menu menuReference;
	
	public MenuDisplay(Menu menu){
		menuReference = menu;
		for (JButton b : menuReference.getMenuButtons()){
			b.addActionListener(new MenuButtonsListener(menu));
			//b.addKeyListener()
			add(b);
		}
		menu.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
