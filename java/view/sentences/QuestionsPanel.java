package view.sentences;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.Sentences;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedLabel;
import view.customized_widgets.CustomizedRadioButton;


public class QuestionsPanel extends DefaultGridPanel {

	private CustomizedLabel sentenceLabel;
	private CustomizedLabel informationLabel;
	private CustomizedButton nextButton;
	private Sentences sentences;
	private int sentenceIsCorrect; // 0 : no, 1 : yes, 2 : question have not been answered
	private boolean isMcq = true;
	private ButtonGroup radioGroup;
	private List<CustomizedRadioButton> choicesRadio;

	public QuestionsPanel(Sentences sentences, boolean isMcq) {
		super();
		this.sentences = sentences;
		this.isMcq = isMcq;

		sentenceLabel = new CustomizedLabel(sentences.getWrongSentence(), (int)Component.CENTER_ALIGNMENT);
		sentenceLabel.setFont(StyleParameters.defaultSentenceFont);
		sentenceLabel.addMouseListener(new SentenceLabelListener());
		informationLabel = new CustomizedLabel("", (int)Component.CENTER_ALIGNMENT);
		informationLabel.setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 20));
		nextButton = new CustomizedButton("Next sentence");
		nextButton.addActionListener(new NextButtonListener());
		
		addComponent(sentenceLabel, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(informationLabel, 0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(nextButton, 0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		goToNextQuestion();
		
		setVisible(true);
	}
	
	public void reset() {
		
		goToNextQuestion();
	}
	
	public void goToNextQuestion() {

		informationLabel.setText("");
		sentenceIsCorrect = 2;
		nextButton.setVisible(false);
		
		if (!isMcq) {
			sentenceLabel.setText("<html>" + sentences.getWrongSentence() + "</html>");
		}
		else {
			if (choicesRadio != null)
				for (CustomizedRadioButton radio : choicesRadio)
					remove(radio);

			sentenceLabel.setText("<html><center>" + sentences.getIncompleteWrongSentence() + "</center></html>");
			choicesRadio = new ArrayList<>();
			
			ArrayList<String> choices = sentences.getChoices();
			radioGroup = new ButtonGroup();
			
			int i = 1;
			
			for (String choice : choices) {

				CustomizedRadioButton radio = new CustomizedRadioButton(choice);
				radio.addActionListener(new ChoiceListener(radio));
				
				choicesRadio.add(radio);
				radioGroup.add(radio);
				add(radio, i++);
			}

			revalidate();
			repaint();
		}


		if (sentences.isFinished() && getParent() != null)
			((SentencesPanel)getParent()).goToEndPanel();
	}
	
	public class SentenceLabelListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent event) {
			
			if (sentenceIsCorrect == 2 && !isMcq) {

				int characterWidth = sentenceLabel.getWidth() / sentenceLabel.getText().length();
				int index = event.getX() / characterWidth;	// ID of the clicked character

				if (sentences.characterIsFromWrongWord(index)) {
					informationLabel.setText("<html><center>Bad answer</center><br>Good sentence : <i>" + sentences.getCorrectSentence().replaceAll("colorToChange", "red") + "</i>");
					sentenceIsCorrect = 0;
				}
				else {
					informationLabel.setText("<html><center>Well done !</center><br>Good sentence : <i>" + sentences.getCorrectSentence().replaceAll("colorToChange", "green") + "</i>");
					sentenceIsCorrect = 1;
				}

				informationLabel.setText(informationLabel.getText() + "<br><br>Rule : " + sentences.getRuleName() + "<br><br><p style='padding-left: 20px;'><i>" + sentences.getRule().replaceAll("\n", "<br>") + "</i></p></html>");
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
					informationLabel.setText("<html><center>Well done !</center><br>Good sentence : <i>" + sentences.getCorrectSentence().replaceAll("colorToChange", "green") + "</i>");
					sentenceIsCorrect = 1;
				}
				else {
					informationLabel.setText("<html><center>Bad answer</center><br>Good sentence : <i>" + sentences.getCorrectSentence().replaceAll("colorToChange", "red") + "</i>");
					sentenceIsCorrect = 0;
				}

				informationLabel.setText(informationLabel.getText() + "<br><br>Rule : " + sentences.getRuleName() + "<br><br><i>" + sentences.getRule() + "</i></html>");
				nextButton.setVisible(true);
			}
		}
	}
}