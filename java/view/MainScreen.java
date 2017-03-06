package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Menu;
import model.Sentences;
import model.SentencesManager;

@SuppressWarnings("serial")
public class MainScreen extends JFrame {

	private MenuDisplay menuPanel;
	private JPanel diversPannel;
	private GridBagConstraints gbc;
	private JPanel box;
	private boolean stateConnection = false;
	private Sentences sentences;
	private SentencesManager sentencesManager;
	private SentencesPanel sentencesPanel;

	CardLayout cl = new CardLayout();
	JPanel content = new JPanel();
	String[] listContent = { "Nouvelle regle", "TOEIC", "Phrases", "Default" };

	public MainScreen() {
		this.setTitle("Shake's Pear");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		sentences = new Sentences();

		// Initialisation de la vue
		box = new JPanel();
		box.setPreferredSize(new Dimension(1000, 700));
		box.setBackground(Color.gray);
		box.setLayout(new GridBagLayout()); // objet pour le positionnement
		gbc = new GridBagConstraints();

		// Initialisation des diff�rents panels
		JPanel logoPannel = new JPanel();
		logoPannel.setBackground(Color.cyan);
		logoPannel.setPreferredSize(new Dimension(150, 150));

		Menu menu = new Menu(this);
		menuPanel = new MenuDisplay(menu);
		menuPanel.setBackground(Color.white);
		menuPanel.setPreferredSize(new Dimension(150, 450));

		diversPannel = new JPanel();
		diversPannel.setLayout(new FlowLayout(FlowLayout.LEFT));
		diversPannel.setBackground(Color.cyan);
		diversPannel.setPreferredSize(new Dimension(750, 450));

		// Positionnement dans la fen�tre
		// ligne 0 : logo -> Recherche
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		box.add(logoPannel, gbc);

		gbc.gridx = 1;

		// ligne 1 : Menu, on laisse l'espace vide pour les divers afin de le
		// "remplir" dans les diff�rentes classes
		gbc.gridx = 0;
		gbc.gridy = 1;
		box.add(menuPanel, gbc);

		gbc.gridx = 1;
		box.add(diversPannel, gbc);

		this.setContentPane(box);

		sentencesManager = new SentencesManager();
		JPanel sentencesManagerPanel = new SentencesManagerPanel(sentencesManager);
		sentencesPanel = new SentencesPanel(this, sentences);
		// JPanel profil = new ProfileJPanel();
		JPanel defaut = new JPanel();
		JLabel bienvenue = new JLabel("Bienvenue sur Shake's Pear !");
		Font police = new Font("Arial", Font.BOLD, 18);
		bienvenue.setFont(police);
		defaut.add(bienvenue);

		// On d�finit le layout
		content.setLayout(cl);
		// On ajoute les cartes � la pile avec un nom pour les retrouver
		content.add(sentencesManagerPanel, listContent[0]);
		content.add(sentencesPanel, listContent[2]);
		content.add(defaut, listContent[listContent.length - 1]);

		// diversPannel.add(boutonPane, BorderLayout.NORTH);
		diversPannel.add(content, BorderLayout.CENTER);

		// Menu
		menuPanel.setLayout(new GridLayout(menu.getMenuButtons().size(), 1));

		// Logo
		BufferedImage im = null;
		try {
			im = ImageIO.read(this.getClass().getResource("/logoAnglais.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		logoPannel.add(new LogoButton(this, im));

		cl.show(content, listContent[listContent.length - 1]);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public boolean isStateConnection() {
		return stateConnection;
	}

	public void setStateConnection(boolean stateConnection) {
		this.stateConnection = stateConnection;
	}

	public JPanel getContent() {
		return content;
	}

	public void setContent(JPanel content) {
		this.content = content;
	}

	public String[] getListContent() {
		return listContent;
	}

	public void setListContent(String[] listContent) {
		this.listContent = listContent;
	}

	public CardLayout getCl() {
		return cl;
	}

	public void update(Object o, Object arg) {
		if (o instanceof Menu) {
			if (arg.equals("Nouvelle regle")) {
				cl.show(content, listContent[0]);
			}
			else if (arg.equals("Phrases")) {
				cl.show(content, listContent[2]);
				sentences.initialize();
				sentencesPanel.reset();
			}
		} else if (o instanceof LogoButton) {
			cl.show(content, listContent[listContent.length - 1]);

		}
	}

}
