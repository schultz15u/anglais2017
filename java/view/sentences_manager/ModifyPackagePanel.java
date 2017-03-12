package view.sentences_manager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.SentencesManager;
import view.DefaultGridPanel;


public class ModifyPackagePanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
	private JButton addSentenceButton;
	private JButton modifySentenceButton;
	private JButton removeSentenceButton;
	
	public ModifyPackagePanel(SentencesManager sentencesManager) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		
		addSentenceButton = new JButton("Add sentence");
		modifySentenceButton = new JButton("Create sentence");
		removeSentenceButton = new JButton("Modify sentence");
		
		addComponent(addSentenceButton, 0, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(modifySentenceButton, 1, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(removeSentenceButton, 2, 0, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(new JPanel(), 0, 1, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}
}
