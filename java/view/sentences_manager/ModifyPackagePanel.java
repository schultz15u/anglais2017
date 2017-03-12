package view.sentences_manager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import model.SentencesManager;
import view.DefaultGridPanel;


public class ModifyPackagePanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
	private JComboBox packagesCombo;
	private JButton addSentenceButton;
	private JButton modifySentenceButton;
	private JButton removeSentenceButton;
	private AddSentencePanel addSentencePanel;
	private ModifySentencePanel modifySentencePanel;
	private JPanel currentPanel;
	private JLabel messageLabel;
	
	public ModifyPackagePanel(SentencesManager sentencesManager, JLabel messageLabel) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;

		packagesCombo = new JComboBox(sentencesManager.getPackagesNames().toArray());
		packagesCombo.addActionListener(new PackagesComboListener());
		addSentenceButton = new JButton("Add sentence");
		addSentenceButton.addActionListener(new AddSentenceListener());
		modifySentenceButton = new JButton("Modify sentence");
		modifySentenceButton.addActionListener(new ModifySentenceListener());
		removeSentenceButton = new JButton("Remove sentence");

		addSentencePanel = new AddSentencePanel(sentencesManager, messageLabel);
		addSentencePanel.setPackageName(packagesCombo.getItemAt(0).toString());
		modifySentencePanel = new ModifySentencePanel(sentencesManager, messageLabel);
		modifySentencePanel.setPackageName(packagesCombo.getItemAt(0).toString());
		currentPanel = addSentencePanel;

		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(addSentenceButton, 0, 1, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(modifySentenceButton, 1, 1, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(removeSentenceButton, 2, 1, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(addSentencePanel, 0, 2, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	public void update() {
		remove(packagesCombo);
		packagesCombo = new JComboBox(sentencesManager.getPackagesNames().toArray());
		packagesCombo.addActionListener(new PackagesComboListener());
		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		validate();
		repaint();

		addSentencePanel.update();
		modifySentencePanel.update();
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
			if (packagesCombo.getSelectedItem() != null)
				addSentencePanel.setPackageName(packagesCombo.getSelectedItem().toString());
			if (packagesCombo.getSelectedItem() != null)
				modifySentencePanel.setPackageName(packagesCombo.getSelectedItem().toString());
			addSentencePanel.update();
			modifySentencePanel.update();
		}
	}

	private class AddSentenceListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(addSentencePanel, GridBagConstraints.NONE);
			messageLabel.setText("");
		}
	}

	private class ModifySentenceListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(modifySentencePanel, GridBagConstraints.NONE);
			messageLabel.setText("");
		}
	}
}
