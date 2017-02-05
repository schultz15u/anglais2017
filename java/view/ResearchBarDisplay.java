package view;

import javax.swing.JPanel;

import controller.ResearchBarListener;
import controller.ResearchButtonListener;
import model.ResearchBar;

@SuppressWarnings("serial")
public class ResearchBarDisplay extends JPanel {

	private ResearchBar barReference;
	
	public ResearchBarDisplay(ResearchBar bar){
		barReference = bar;
		add(barReference.getLabel());
		add(barReference.getResearchBar());
		barReference.getB().addActionListener(new ResearchButtonListener(barReference));
		barReference.getResearchBar().addKeyListener(new ResearchBarListener(barReference));
		add(barReference.getB());
	}
	
	public ResearchBar getBarModel(){
		return barReference;
	}
	
}
