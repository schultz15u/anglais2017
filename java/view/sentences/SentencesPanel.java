package view.sentences;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Sentences;


public class SentencesPanel extends JPanel {

	private JLabel sentenceLabel;
	private JLabel informationLabel;
	private JLabel ruleLabel;
	private JButton nextButton;
	private Sentences sentences;
	private int sentenceIsCorrect; // 0 : no, 1 : yes, 2 : question have not been answered
	private boolean isMcq = true;
	private ButtonGroup radioGroup;
	private List<JRadioButton> choicesRadio;

	public SentencesPanel(Sentences sentences, boolean isMcq) {
		super();
		
		this.sentences = sentences;
		this.isMcq = isMcq;
		sentences.initialize();
		
		Font font = new Font("Consolas", Font.BOLD, 30);
		sentenceLabel = new JLabel(sentences.getWrongSentence());
		sentenceLabel.setFont(font);
		sentenceLabel.addMouseListener(new SentenceLabelListener());
		sentenceLabel.setBackground(Color.orange);
		sentenceLabel.setOpaque(true);
		
		informationLabel = new JLabel("Informations");
		ruleLabel = new JLabel("");
		
		nextButton = new JButton("Next sentence");
		nextButton.addActionListener(new NextButtonListener());
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(sentenceLabel);
		add(informationLabel);
		add(nextButton);
		add(ruleLabel);
		
		goToNextQuestion();
		
		setVisible(true);
		setPreferredSize(new Dimension(750, 500));
	}
	
	public void reset() {
		sentenceLabel.setText(sentences.getWrongSentence());
		informationLabel.setText(sentences.isFinished() ? "Your score : " + sentences.getScore() : "");
		sentenceIsCorrect = 2;
		nextButton.setVisible(false);
		
		goToNextQuestion();
	}
	
	public void goToNextQuestion() {
		
		informationLabel.setText(sentences.isFinished() ? "Your score : " + sentences.getScore() : "");
		sentenceIsCorrect = 2;
		nextButton.setVisible(false);
		ruleLabel.setText("");
		
		if (!isMcq) {
			sentenceLabel.setText(sentences.getWrongSentence());
		}
		else {
			if (choicesRadio != null)
				for (JRadioButton radio : choicesRadio)
					remove(radio);
			
			sentenceLabel.setText(sentences.getIncompleteWrongSentence());
			choicesRadio = new ArrayList<JRadioButton>();
			
			ArrayList<String> choices = sentences.getChoices();
			radioGroup = new ButtonGroup();
			
			int i = 1;
			
			for (String choice : choices) {
				
				JRadioButton radio = new JRadioButton(choice);
				radio.addActionListener(new ChoiceListener(radio));
				
				choicesRadio.add(radio);
				radioGroup.add(radio);
				add(radio, i++);
			}

			revalidate();
			repaint();
		}
	}
	
	public class SentenceLabelListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent event) {
			
			if (sentenceIsCorrect == 2 && !isMcq) {
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

				ruleLabel.setText("Rule : " + sentences.getRule());
				nextButton.setVisible(true);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	public class NextButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			sentences.validateSentence(sentenceIsCorrect == 1);
			goToNextQuestion();
		}
	}
	
	public class ChoiceListener implements ActionListener
	{
		JRadioButton button;
		
		public ChoiceListener(JRadioButton button) {
			super();
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (sentenceIsCorrect == 2 && isMcq) {

				if (sentences.getCorrectWord().equals(button.getText())) {
					informationLabel.setText("Correct !");
					sentenceIsCorrect = 1;
				}
				else {
					informationLabel.setText("Wrong !");
					sentenceIsCorrect = 0;
				}

				ruleLabel.setText("Rule : " + sentences.getRule());
				nextButton.setVisible(true);
			}
		}
	}
}