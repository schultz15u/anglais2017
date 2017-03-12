package view.sentences_manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import model.SentencesManager;
import view.DefaultGridPanel;
import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedComboBox;


public class ModifyPackagePanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
	private CustomizedComboBox packagesCombo;
	private CustomizedButton addSentenceButton;
	private CustomizedButton modifySentenceButton;
	private CustomizedButton removeSentenceButton;
	private AddSentencePanel addSentencePanel;
	private ModifySentencePanel modifySentencePanel;
	private RemoveSentencePanel removeSentencePanel;
	private JPanel currentPanel;
	private JLabel messageLabel;
	
	public ModifyPackagePanel(SentencesManager sentencesManager, JLabel messageLabel) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;
		setBackground(new Color(30, 30, 30));

		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames().toArray());
		packagesCombo.addActionListener(new PackagesComboListener());
		addSentenceButton = new CustomizedButton("Add sentence");
		addSentenceButton.addActionListener(new AddSentenceListener());
		modifySentenceButton = new CustomizedButton("Modify sentence");
		modifySentenceButton.addActionListener(new ModifySentenceListener());
		removeSentenceButton = new CustomizedButton("Remove sentence");
		removeSentenceButton.addActionListener(new RemoveSentenceListener());

		addSentencePanel = new AddSentencePanel(sentencesManager, messageLabel);
		modifySentencePanel = new ModifySentencePanel(sentencesManager, messageLabel);
		removeSentencePanel = new RemoveSentencePanel(sentencesManager, messageLabel);
		currentPanel = addSentencePanel;

		if (packagesCombo.getSelectedItem() != null) {
			addSentencePanel.setPackageName(packagesCombo.getItemAt(0).toString());
			modifySentencePanel.setPackageName(packagesCombo.getItemAt(0).toString());
			removeSentencePanel.setPackageName(packagesCombo.getItemAt(0).toString());
		}

		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(addSentenceButton, 0, 1, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(modifySentenceButton, 1, 1, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(removeSentenceButton, 2, 1, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(addSentencePanel, 0, 2, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		addSentenceButton.setNormalColor(new Color(80, 80, 80));
	}

	public void update() {
		remove(packagesCombo);
		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames().toArray());
		packagesCombo.addActionListener(new PackagesComboListener());
		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		validate();
		repaint();

		addSentencePanel.update();
		modifySentencePanel.update();
		removeSentencePanel.update();
	}

	private void changePanel(JPanel newPanel, int fill) {
		remove(currentPanel);
		currentPanel = newPanel;
		addComponent(currentPanel, 0, 2, 3, 1, 1, 1, GridBagConstraints.CENTER, fill);
		messageLabel.setText("");

		revalidate();
		repaint();
	}

	private class PackagesComboListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (packagesCombo.getSelectedItem() != null) {
				addSentencePanel.setPackageName(packagesCombo.getSelectedItem().toString());
				modifySentencePanel.setPackageName(packagesCombo.getSelectedItem().toString());
				removeSentencePanel.setPackageName(packagesCombo.getSelectedItem().toString());
			}

			addSentencePanel.update();
			modifySentencePanel.update();
			removeSentencePanel.update();
		}
	}

	private class AddSentenceListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(addSentencePanel, GridBagConstraints.NONE);
			messageLabel.setText("");
			addSentencePanel.update();
			modifySentencePanel.update();
			removeSentencePanel.update();

			addSentenceButton.setNormalColor(new Color(50, 50, 50));
			modifySentenceButton.setNormalColor(new Color(50, 50, 50));
			removeSentenceButton.setNormalColor(new Color(50, 50, 50));
			addSentenceButton.setNormalColor(new Color(80, 80, 80));
		}
	}

	private class ModifySentenceListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(modifySentencePanel, GridBagConstraints.NONE);
			messageLabel.setText("");
			addSentencePanel.update();
			modifySentencePanel.update();
			removeSentencePanel.update();

			addSentenceButton.setNormalColor(new Color(50, 50, 50));
			modifySentenceButton.setNormalColor(new Color(50, 50, 50));
			removeSentenceButton.setNormalColor(new Color(50, 50, 50));
			modifySentenceButton.setNormalColor(new Color(80, 80, 80));
		}
	}

	private class RemoveSentenceListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(removeSentencePanel, GridBagConstraints.NONE);
			messageLabel.setText("");
			addSentencePanel.update();
			modifySentencePanel.update();
			removeSentencePanel.update();

			addSentenceButton.setNormalColor(new Color(50, 50, 50));
			modifySentenceButton.setNormalColor(new Color(50, 50, 50));
			removeSentenceButton.setNormalColor(new Color(50, 50, 50));
			removeSentenceButton.setNormalColor(new Color(80, 80, 80));
		}
	}
}
