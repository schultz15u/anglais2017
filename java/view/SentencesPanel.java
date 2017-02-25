package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Sentences;


public class SentencesPanel extends JPanel {

	private JLabel sentenceLabel;
	private JLabel informationLabel;
	private JButton nextButton;
	private Sentences sentences;
	private int sentenceIsCorrect; // 0 : no, 1 : yes, 2 : question have not been answered

	public SentencesPanel(MainScreen screen, Sentences sentences) {
		super();
		
		this.sentences = sentences;
		sentences.initialize();
		sentenceIsCorrect = 2;
		
		Font font = new Font("Consolas", Font.BOLD, 30);
		sentenceLabel = new JLabel(sentences.getWrongSentence());
		sentenceLabel.setFont(font);
		sentenceLabel.addMouseListener(new SentenceLabelListener());
		sentenceLabel.setBackground(Color.orange);
		sentenceLabel.setOpaque(true);
		
		informationLabel = new JLabel("Informations");
		
		nextButton = new JButton("Next sentence");
		nextButton.addMouseListener(new NextButtonListener());
		nextButton.setVisible(false);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(sentenceLabel);
		add(informationLabel);
		add(nextButton);
		
		this.setVisible(true);
	}
	
	public void reset() {
		sentenceLabel.setText(sentences.getWrongSentence());
		informationLabel.setText(sentences.isFinished() ? "Your score : " + sentences.getScore() : "");
		sentenceIsCorrect = 2;
		nextButton.setVisible(false);
	}
	
	public class SentenceLabelListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent event) {
			
			if (sentenceIsCorrect == 2) {
				int characterWidth = sentenceLabel.getWidth() / sentenceLabel.getText().length();
				int index = event.getX() / characterWidth;	// ID of the clicked character
				
				if (sentences.characterIsFromWrongWord(index)) {
					informationLabel.setText("Correct !");
					sentenceIsCorrect = 1;
				}
				else {
					informationLabel.setText("Wrong !");
					sentenceIsCorrect = 0;
				}
				
				nextButton.setVisible(true);
			}
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
	
	public class NextButtonListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent event) {
			
			sentences.validateSentence(sentenceIsCorrect == 1);
			sentenceLabel.setText(sentences.getWrongSentence());
			informationLabel.setText(sentences.isFinished() ? "Your score : " + sentences.getScore() : "");
			sentenceIsCorrect = 2;
			nextButton.setVisible(false);
			
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