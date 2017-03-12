package view.sentences_manager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

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
		
		createPackagePanel = new CreatePackagePanel(sentencesManager);
		modifyPackagePanel = new ModifyPackagePanel(sentencesManager);
		currentPanel = createPackagePanel;
		
		addComponent(importPackageButton, 0, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(createPackageButton, 1, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(modifyPackageButton, 2, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(removePackageButton, 3, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(createPackagePanel, 0, 1, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}
	
	private class CreatePackageListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			remove(currentPanel);
			currentPanel = createPackagePanel;
			addComponent(currentPanel, 0, 1, 4, 1);
			revalidate();
			repaint();
		}
	}
	
	private class ModifyPackageListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			remove(currentPanel);
			currentPanel = modifyPackagePanel;
			addComponent(currentPanel, 0, 1, 4, 1);
			revalidate();
			repaint();
		}
	}
}
