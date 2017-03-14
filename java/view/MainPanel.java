package view;

import model.SentencesManager;
import view.customized_widgets.CustomizedButton;
import view.sentences_manager.CreatePackagePanel;
import view.sentences_manager.ModifyPackagePanel;
import view.sentences_manager.RemovePackagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainPanel extends DefaultGridPanel {

	public MainPanel(SentencesManager sentencesManager) {
		
		super();
		setLayout(new GridBagLayout());
		setBackground(new Color(30, 30, 30));
	}
}
