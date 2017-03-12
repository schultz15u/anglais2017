package view.sentences_manager;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.SentencesManager;
import view.DefaultGridPanel;


public class CreatePackagePanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton validationButton;
	
	public CreatePackagePanel(SentencesManager sentencesManager) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		
		nameLabel = new JLabel("Package name : ");
		nameField = new JTextField("", 30);
		validationButton = new JButton("Create");
		
		addComponent(nameLabel, 0, 0, 1, 1, 1, 1);
		addComponent(nameField, 1, 0, 1, 1);
		addComponent(validationButton, 0, 1, 2, 1);
	}
}
