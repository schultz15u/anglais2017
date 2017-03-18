package view.sentences_manager;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListDataListener;

import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
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

		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames());
		packagesCombo.addActionListener(new PackagesComboListener());
		addSentenceButton = new CustomizedButton("Add sentence");
		addSentenceButton.addActionListener(new AddSentenceListener());
		addSentenceButton.setPreferredSize(new Dimension(addSentenceButton.getWidth(), 40));
		modifySentenceButton = new CustomizedButton("Modify sentence");
		modifySentenceButton.addActionListener(new ModifySentenceListener());
		modifySentenceButton.setPreferredSize(new Dimension(modifySentenceButton.getWidth(), 40));
		removeSentenceButton = new CustomizedButton("Remove sentence");
		removeSentenceButton.addActionListener(new RemoveSentenceListener());
		removeSentenceButton.setPreferredSize(new Dimension(removeSentenceButton.getWidth(), 40));

		addSentencePanel = new AddSentencePanel(sentencesManager, messageLabel);
		modifySentencePanel = new ModifySentencePanel(sentencesManager, messageLabel);
		removeSentencePanel = new RemoveSentencePanel(sentencesManager, messageLabel);
		currentPanel = addSentencePanel;

		if (packagesCombo.getSelectedItem() != null) {
			addSentencePanel.setPackageName(packagesCombo.getItemAt(0));
			modifySentencePanel.setPackageName(packagesCombo.getItemAt(0));
			removeSentencePanel.setPackageName(packagesCombo.getItemAt(0));
		}

		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(addSentenceButton, 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(modifySentenceButton, 1, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(removeSentenceButton, 2, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(addSentencePanel, 0, 2, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		addSentenceButton.setNormalColor(StyleParameters.defaultWidgetBackgroundColor);
		modifySentenceButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		removeSentenceButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);

		packagesCombo.setMaximumSize(new Dimension((int) packagesCombo.getPreferredSize().getWidth(), 40));
		packagesCombo.setMinimumSize(new Dimension((int) packagesCombo.getPreferredSize().getWidth(), 40));
		packagesCombo.setPreferredSize(new Dimension((int) packagesCombo.getPreferredSize().getWidth(), 40));
		packagesCombo.setSize(new Dimension((int) packagesCombo.getPreferredSize().getWidth(), 40));

		addSentenceButton.setMaximumSize(new Dimension((int) addSentenceButton.getPreferredSize().getWidth(), 40));
		addSentenceButton.setMinimumSize(new Dimension((int) addSentenceButton.getPreferredSize().getWidth(), 40));
		addSentenceButton.setPreferredSize(new Dimension((int) addSentenceButton.getPreferredSize().getWidth(), 40));
		addSentenceButton.setSize(new Dimension((int) addSentenceButton.getPreferredSize().getWidth(), 40));
		modifySentenceButton.setMaximumSize(new Dimension((int) modifySentenceButton.getPreferredSize().getWidth(), 40));
		modifySentenceButton.setMinimumSize(new Dimension((int) modifySentenceButton.getPreferredSize().getWidth(), 40));
		modifySentenceButton.setPreferredSize(new Dimension((int) modifySentenceButton.getPreferredSize().getWidth(), 40));
		modifySentenceButton.setSize(new Dimension((int) modifySentenceButton.getPreferredSize().getWidth(), 40));
		removeSentenceButton.setMaximumSize(new Dimension((int) removeSentenceButton.getPreferredSize().getWidth(), 40));
		removeSentenceButton.setMinimumSize(new Dimension((int) removeSentenceButton.getPreferredSize().getWidth(), 40));
		removeSentenceButton.setPreferredSize(new Dimension((int) removeSentenceButton.getPreferredSize().getWidth(), 40));
		removeSentenceButton.setSize(new Dimension((int) removeSentenceButton.getPreferredSize().getWidth(), 40));
	}

	public void update() {

		remove(packagesCombo);
		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames());
		packagesCombo.addActionListener(new PackagesComboListener());
		packagesCombo.setPreferredSize(new Dimension(packagesCombo.getWidth(), 40));
		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		packagesCombo.setMaximumSize(new Dimension((int) packagesCombo.getPreferredSize().getWidth(), 40));
		packagesCombo.setMinimumSize(new Dimension((int) packagesCombo.getPreferredSize().getWidth(), 40));
		packagesCombo.setPreferredSize(new Dimension((int) packagesCombo.getPreferredSize().getWidth(), 40));
		packagesCombo.setSize(new Dimension((int) packagesCombo.getPreferredSize().getWidth(), 40));

		revalidate();
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

	private class AddSentenceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(addSentencePanel, GridBagConstraints.NONE);
			messageLabel.setText("");
			addSentencePanel.update();
			modifySentencePanel.update();
			removeSentencePanel.update();

			addSentenceButton.setNormalColor(StyleParameters.defaultWidgetBackgroundColor);
			modifySentenceButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			removeSentenceButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		}
	}

	private class ModifySentenceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(modifySentencePanel, GridBagConstraints.NONE);
			messageLabel.setText("");
			addSentencePanel.update();
			modifySentencePanel.update();
			removeSentencePanel.update();

			addSentenceButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			modifySentenceButton.setNormalColor(StyleParameters.defaultWidgetBackgroundColor);
			removeSentenceButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		}
	}

	private class RemoveSentenceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(removeSentencePanel, GridBagConstraints.NONE);
			messageLabel.setText("");
			addSentencePanel.update();
			modifySentencePanel.update();
			removeSentencePanel.update();

			addSentenceButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			modifySentenceButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			removeSentenceButton.setNormalColor(StyleParameters.defaultWidgetBackgroundColor);
		}
	}
}
