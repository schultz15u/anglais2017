package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import model.object.CollectionModel;
import model.object.UserModel;

@SuppressWarnings("serial")
public class Authentification extends JPanel {
	
	private JFormattedTextField pseudoField = new JFormattedTextField();
	private JPasswordField mdpField = new JPasswordField();
	private JLabel pseudoLabel = new JLabel("Pseudo : ");
	private JLabel mdpLabel = new JLabel("Mot de passe : ");
	private JButton coButton = new JButton("CONNEXION");
	private MainScreen screen;
	
	public Authentification(MainScreen screen) {
		super();
		this.screen = screen;
	    // Mise en place de diversPannel en bas à droite par rapport a MainScreen 
	    GridBagConstraints gbc = new GridBagConstraints();
	    this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(750, 500));
	    
	    //bloc haut
	    JPanel espaceHaut = new JPanel();
	    espaceHaut.setBackground(Color.white);
	    espaceHaut.setPreferredSize(new Dimension(750, 125));
	    
	    //PseudoField
	    Font police = new Font("Arial", Font.BOLD, 14);
		pseudoField.setFont(police);
		pseudoField.setPreferredSize(new Dimension(150, 30));
		pseudoField.setForeground(Color.black);
		
		//MdpField
		mdpField.setFont(police);
		mdpField.setPreferredSize(new Dimension(150, 30));
		mdpField.setForeground(Color.black);
		
	    //bloc contenant l'information
	    JPanel infos = new JPanel();
	    infos.setLayout(new GridLayout(2, 2));
	    infos.add(pseudoLabel);
	    infos.add(pseudoField);
	    infos.add(mdpLabel);
	    infos.add(mdpField);
	    
	    // Mise en place de diversPannel
	    // ligne 0 : bloc d'espace pour centre en hauteur le texte
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridheight = GridBagConstraints.REMAINDER;
	    gbc.gridwidth = 1;
	    this.add(espaceHaut, gbc);
	    
	    // ligne 1 : bloc contenant infos -> bouton de connexion
	    gbc.gridy = 1;
	    this.add(infos,gbc);
	    
	    gbc.gridx = 1;
	    coButton.addActionListener(new BoutonListener());
	    this.add(coButton, gbc);

		this.setVisible(true);
	}
	
	class BoutonListener implements ActionListener {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if ((JButton) e.getSource() == coButton) {
				for (UserModel u : screen.getAllTheUsersRegistered()){
					System.out.println("pseudo : "+u.getUsername()+" | mdp : "+u.getPassword());
					System.out.println("pseudo2 : "+pseudoField.getText()+" | mdp2 : "+mdpField.getText());
					
					if (u.getUsername().equals(pseudoField.getText()) && u.getPassword().equals(mdpField.getText())){
						// si pseudo ok 
						System.out.println("bonjour");
						screen.getCl().show(screen.getContent(), screen.getListContent()[screen.getListContent().length -1] );
						screen.setConnectedUser(u);
						screen.setStateConnection(true);
						System.out.println(screen.isStateConnection());
						//if (screen.isStateConnection()){
							for (CollectionModel p : screen.getMyPlaylists()){
								p.getNameCollection();
							
							//screen.setMyPlaylists(myPlaylists);
							//}
							}
						break;
					}
					else {
						System.out.println("aurevoir");
						System.out.println("Pseudo renseigné : " + pseudoField.getText() + " le mot de passe associé : " + mdpField.getText());
						screen.setStateConnection(false);
						screen.getCl().show(screen.getContent(), screen.getListContent()[0] );
					}
				// si pseudo pas ok
				}
				mdpField.setText("");
			}
		}
	}
}
