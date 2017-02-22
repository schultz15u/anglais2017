package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.accessibility.AccessibleText;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import model.object.RuleModel;


public class Sentences extends JPanel {

	private JLabel sentenceLabel;
	private JLabel informationLabel;
	private MainScreen screen;

	public Sentences(MainScreen screen) {
		super();
		
		this.screen = screen;
		
		Font font = new Font("Consolas", Font.BOLD, 30);
		sentenceLabel = new JLabel("Ma phrase !");
		sentenceLabel.setFont(font);
		sentenceLabel.addMouseListener(new SentenceLabelListener());
		
		informationLabel = new JLabel("Informations");
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 200, 200));
		add(sentenceLabel);
		add(informationLabel);
		
		this.setVisible(true);
	}
	
	public class SentenceLabelListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent event) {
			
			int characterWidth = sentenceLabel.getWidth() / sentenceLabel.getText().length();
			int index = event.getX() / characterWidth;
			
			informationLabel.setText("Letter ID : " + index);
		}

		@Override
		public void mouseEntered(MouseEvent event) {
		}

		@Override
		public void mouseExited(MouseEvent event) {
		}

		@Override
		public void mousePressed(MouseEvent event) {
		}

		@Override
		public void mouseReleased(MouseEvent event) {			
		}
	}
}