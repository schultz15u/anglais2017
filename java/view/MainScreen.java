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
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.DataBaseModel;
import model.Menu;
import model.PlaylistCreation;
import model.ResearchBar;
import model.VideoDescription;
import model.DataBase.ViewDataBase;
import model.object.CollectionModel;
import model.object.UserModel;

@SuppressWarnings("serial")
public class MainScreen extends JFrame implements Observer {

	private ResearchBarDisplay researchPanel;
	private MenuDisplay menuPanel;
	private JPanel diversPannel;
	private GridBagConstraints gbc;
	private JPanel box;
	private JPanel resultsResearch = new JPanel();
	private JPanel playlistsToDisplay = new JPanel();
	private boolean stateConnection = false;
	private ArrayList<UserModel> allTheUsersRegistered = new ArrayList<UserModel>();
	private static UserModel connectedUser;
	private ArrayList<CollectionModel> myPlaylists;
	private PlaylistsJPanel playlists;

	CardLayout cl = new CardLayout();
	JPanel content = new JPanel();
	String[] listContent = { "Authentification", "Profil", "Abonnements", "Playlists", "Mes vidéos", "Inscription",
			"Results", "Défaut" };

	@SuppressWarnings("static-access")
	public MainScreen() {
		//base = D;
		//ViewDataBase V = new ViewDataBase();
		DataBaseModel D = new DataBaseModel();
		allTheUsersRegistered = D.getAllUser();
		ViewDataBase V = new ViewDataBase();
		V.ViewUser(allTheUsersRegistered);
		D.close();
		
		this.setTitle("YT-GP");
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// Initialisation de la vue
		box = new JPanel();
		box.setPreferredSize(new Dimension(1000, 700));
		box.setBackground(Color.gray);
		box.setLayout(new GridBagLayout()); // objet pour le positionnement
		gbc = new GridBagConstraints();

		// Initialisation des différents panels
		JPanel logoPannel = new JPanel();
		logoPannel.setBackground(Color.cyan);
		logoPannel.setPreferredSize(new Dimension(150, 150));

		ResearchBar bar = new ResearchBar(this);
		bar.addObserver(this);
		researchPanel = new ResearchBarDisplay(bar);
		researchPanel.setBackground(Color.darkGray);
		researchPanel.setPreferredSize(new Dimension(750, 150));

		Menu menu = new Menu(this);
		menuPanel = new MenuDisplay(menu);
		menuPanel.setBackground(Color.white);
		menuPanel.setPreferredSize(new Dimension(150, 450));

		diversPannel = new JPanel();
		diversPannel.setLayout(new FlowLayout(FlowLayout.LEFT));
		diversPannel.setBackground(Color.cyan);
		diversPannel.setPreferredSize(new Dimension(750, 450));

		// Positionnement dans la fenêtre
		// ligne 0 : logo -> Recherche
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		box.add(logoPannel, gbc);

		gbc.gridx = 1;
		box.add(researchPanel, gbc);

		// ligne 1 : Menu, on laisse l'espace vide pour les divers afin de le
		// "remplir" dans les différentes classes
		gbc.gridx = 0;
		gbc.gridy = 1;
		box.add(menuPanel, gbc);

		gbc.gridx = 1;
		box.add(diversPannel, gbc);

		this.setContentPane(box);

		JPanel authentification = new Authentification(this);
		JPanel profil = new ProfileJPanel();
		JPanel abonnements = new Abonnements();
		JPanel uploaded = new UploadedPanel();
		JPanel inscription = new Inscription(this);
		JPanel defaut = new JPanel();
		JLabel bienvenue = new JLabel("Bienvenue sur Shakespear !");
		Font police = new Font("Arial", Font.BOLD, 18);
		bienvenue.setFont(police);
		defaut.add(bienvenue);

		// On définit le layout
		content.setLayout(cl);
		// On ajoute les cartes à la pile avec un nom pour les retrouver
		content.add(authentification, listContent[0]);
		content.add(profil, listContent[1]);
		content.add(abonnements, listContent[2]);
		content.add(uploaded, listContent[4]);
		content.add(inscription, listContent[5]);
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
		//LogoButton logoButton = new LogoButton(this.getClass().getResource("/logoAnglais.png"));
		//logoButton.addActionListener(new LogoButtonListener(this));
		//logoPannel.add(logoButton);
		JLabel imIcon = new JLabel(new ImageIcon(im));
		logoPannel.add(imIcon);

		cl.show(content, listContent[listContent.length - 1]);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	


	public UserModel getConnectedUser() {
		return connectedUser;
	}

	@SuppressWarnings("static-access")
	public void setConnectedUser(UserModel connectedUser) {
		this.connectedUser = connectedUser;
	}

	public ArrayList<UserModel> getAllTheUsersRegistered() {
		return allTheUsersRegistered;
	}

	public void setAllTheUsersRegistered(ArrayList<UserModel> allTheUsersRegistered) {
		this.allTheUsersRegistered = allTheUsersRegistered;
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

	public PlaylistsJPanel getPlaylists() {
		return playlists;
	}

	public JPanel getPlaylistsToDisplay() {
		return playlistsToDisplay;
	}
	
	@SuppressWarnings("static-access")
	public ArrayList<CollectionModel> getMyPlaylists() {
		DataBaseModel D = new DataBaseModel();
		myPlaylists = D.getAllCollectionByUser(connectedUser);
		ViewDataBase V = new ViewDataBase();
		V.ViewCollection(myPlaylists);
		D.close();
		return myPlaylists;
	}

	public void setMyPlaylists(ArrayList<CollectionModel> myPlaylists) {
		this.myPlaylists = myPlaylists;
	}

	public void setPlaylists(PlaylistsJPanel playlists) {
		this.playlists = playlists;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Menu) {
			if (arg.equals("Authentification") && !isStateConnection()) {
				cl.show(content, listContent[0]);
			}
			if (arg.equals("Profil") && isStateConnection()) {
				cl.show(content, listContent[1]);
			}
			if (arg.equals("Abonnements") && isStateConnection()) {
				cl.show(content, listContent[2]);
			}
			if (arg.equals("Playlists") && isStateConnection()) {
				playlistsToDisplay = new PlaylistsJPanel(this);
				JPanel liste = playlistsToDisplay;
				content.add(liste, listContent[3]);
				cl.show(content, listContent[3]);
			}
			if (arg.equals("Inscription") && !isStateConnection()) {
				cl.show(content, listContent[5]);
			}
			if (arg.equals("Déconnexion") && isStateConnection()) {
				cl.show(content, listContent[0]);
			}
		}
		if (o instanceof ResearchBar) {
			try {
				resultsResearch = new Results(researchPanel.getBarModel().getResults(), this);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			JPanel results = resultsResearch;
			content.add(results, listContent[6]);
			cl.show(content, listContent[6]);
		}
		if (o instanceof VideoDescription) {
			JFrame frame = new JFrame();
			frame.setTitle("Description de la vidéo");
			frame.setSize(800, 500);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setResizable(true);
			JPanel panel = new JPanel();
			
			panel.setBackground(Color.white);
			panel.setLayout(new GridLayout(0,2));
	
			panel.add(new JLabel("Titre : "+((VideoDescription) o).getvideoName()));
			panel.add(new JLabel());
			panel.add(new JLabel(((VideoDescription) o).getDate()));
			panel.add(new JLabel());
			panel.add(new JLabel("Description : "+((VideoDescription) o).getDescription()));
			panel.add(new JLabel());
			panel.add(new JLabel());
			
			frame.add(panel);
			
			panel.add(new JLabel());
			panel.add(new JLabel("Commentaires : "));
			panel.add(new JLabel());
			panel.add(new JLabel());
			
			panel.add(new JLabel());
			panel.add(new JButton("s'abonner"));
			panel.add(new JButton("ajouter aux favoris"));
			panel.add(new JButton("enregistrer dans une playlist"));
			
			panel.add(new JButton("ajouter un commentaire"));
			
			
			frame.setVisible(true);
		}
		if (o instanceof PlaylistCreation) {
			playlistsToDisplay = new PlaylistsJPanel(this);
			JPanel liste = playlistsToDisplay;
			content.add(liste, listContent[3]);
			cl.show(content, listContent[3]);
		}
	}

}
