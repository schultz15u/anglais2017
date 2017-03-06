package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;


public class SentencesManagerPanel extends JPanel {
	
	JButton button;
	
	public SentencesManagerPanel() {
		super();

		button = new JButton("Test");		
		button.setPreferredSize(new Dimension(200, 50));
		button.setBackground(Color.blue);
		button.setForeground(Color.white);
		button.setFocusPainted(false);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		
		add(button);
	}
}
