package view.sentences_manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.SentencesManager;
import view.DefaultGridPanel;


public class SentencesManagerPanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
	private JButton importPackageButton;
	private JButton createPackageButton;
	private JButton modifyPackageButton;
	private JButton removePackageButton;
	private CreatePackagePanel createPackagePanel;
	private ModifyPackagePanel modifyPackagePanel;
	private JPanel currentPanel;
	private JLabel messageLabel;
	
	public SentencesManagerPanel(SentencesManager sentencesManager) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		
		importPackageButton = new JButton("Import package");
		createPackageButton = new JButton("Create package");
		createPackageButton.addActionListener(new CreatePackageListener());
		modifyPackageButton = new JButton("Modify package");
		modifyPackageButton.addActionListener(new ModifyPackageListener());
		removePackageButton = new JButton("Remove package");
		messageLabel = new JLabel("");

		createPackagePanel = new CreatePackagePanel(sentencesManager, messageLabel);
		modifyPackagePanel = new ModifyPackagePanel(sentencesManager, messageLabel);
		currentPanel = createPackagePanel;
		
		addComponent(importPackageButton, 0, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(createPackageButton, 1, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(modifyPackageButton, 2, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(removePackageButton, 3, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(createPackagePanel, 0, 1, 4, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(messageLabel, 0, 2, 4, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
	}

	private void changePanel(JPanel newPanel, int fill) {
		remove(currentPanel);
		currentPanel = newPanel;
		addComponent(currentPanel, 0, 1, 4, 1, 1, 1, GridBagConstraints.CENTER, fill);
		messageLabel.setText("");

		revalidate();
		repaint();
	}
	
	private class CreatePackageListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(createPackagePanel, GridBagConstraints.NONE);
		}
	}
	
	private class ModifyPackageListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(modifyPackagePanel, GridBagConstraints.BOTH);
			modifyPackagePanel.update();
		}
	}
}
