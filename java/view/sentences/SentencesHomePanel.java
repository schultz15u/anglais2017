package view.sentences;

import model.Sentences;
import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedComboBox;
import view.customized_widgets.CustomizedLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


public class SentencesHomePanel extends DefaultGridPanel {

	private SentencesManager sentencesManager;
	private CustomizedLabel sentenceLabel;
	private CustomizedButton nextButton;
	private CustomizedComboBox packagesCombo;
	private Sentences sentences;

	private static String mcqMessage = "<html>For each sentence, you must choose the best answer.<br />Ready ?</html>";
	private static String mistakesMessage = "<html>For each sentence, you have to click on the mistake.<br />Ready ?</html>";

	public SentencesHomePanel(SentencesManager sentencesManager, boolean isMcq) {

		super();
		this.sentencesManager = sentencesManager;
		setBackground(StyleParameters.defaultBackgroundColor);

		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames().toArray());
		sentenceLabel = new CustomizedLabel(isMcq ? mcqMessage : mistakesMessage);
		nextButton = new CustomizedButton("Start");
		nextButton.addActionListener(new StartListener());


		addComponent(packagesCombo, 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(sentenceLabel, 0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(nextButton, 0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	public void update() {
		remove(packagesCombo);
		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames().toArray());
		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		validate();
		repaint();
	}
	
	public class StartListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			((SentencesPanel)getParent()).goToQuestionsPanel(packagesCombo.getSelectedItem().toString());
		}
	}
}