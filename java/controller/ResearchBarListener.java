package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.ResearchBar;

public class ResearchBarListener implements KeyListener{

	private ResearchBar barReference;
	
	public ResearchBarListener(ResearchBar bar) {
		barReference = bar;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			barReference.generateResults();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
