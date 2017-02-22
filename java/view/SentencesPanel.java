package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SentencesPanel extends JPanel {

	private JLabel sentenceLabel;
	private JLabel informationLabel;

	public SentencesPanel(MainScreen screen) {
		super();
		
		Font font = new Font("Consolas", Font.BOLD, 30);
		sentenceLabel = new JLabel("There is an error in this sentance !");
		sentenceLabel.setFont(font);
		sentenceLabel.addMouseListener(new SentenceLabelListener());
		
		
		sentenceLabel.setBackground(Color.orange);
		sentenceLabel.setOpaque(true);
		
		informationLabel = new JLabel("Informations");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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