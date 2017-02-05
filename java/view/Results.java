package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.VideoDescription;

@SuppressWarnings("serial")
public class Results extends JPanel {
	
	private ArrayList<VideoDescription> resultsList;

	public Results(ArrayList<VideoDescription> resultat, MainScreen screen) throws Exception {
		resultsList = resultat;
		JPanel resultats = new JPanel();
		resultats.setBackground(Color.white);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		int numero = 0;
		
		JScrollPane scroll = new JScrollPane(resultats);
		scroll.getHorizontalScrollBar().setUnitIncrement(10);
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		
		this.setPreferredSize(new Dimension(750, 500));
		resultats.setPreferredSize(new Dimension(1100, 2400));
		scroll.setPreferredSize(new Dimension(728, 435));
		resultats.setLayout(new FlowLayout(FlowLayout.LEFT));

		
		for (VideoDescription i : resultsList) {
			gbc.gridy = numero;
			VideoDescriptionDisplay vdd = new VideoDescriptionDisplay(i);
			resultats.add(vdd);
			numero = numero + 1;
			i.addObserver(screen);
		}

		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		// On ajoute l'objet au content pane de notre fenêtre
		this.add(scroll);

	}
}
