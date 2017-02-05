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


@SuppressWarnings("serial")
public class ProfileJPanel extends JPanel {
	
	
	
	private JFormattedTextField mailField;
	private JFormattedTextField pseudoField;
	private JPasswordField mdpField = new JPasswordField();
	private JLabel mailLabel = new JLabel("Adresse e-mail : ");
	private JLabel pseudoLabel = new JLabel("Pseudo : ");
	private JLabel mdpLabel = new JLabel("Mot de passe : ");
	private JButton coButton = new JButton("MODIFIER");
	
	public ProfileJPanel() {
		super();
	    // Mise en place de diversPannel en bas à droite par rapport a MainScreen 
	    GridBagConstraints gbc = new GridBagConstraints();
	    this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(750, 500));
	    
	    //bloc haut
	    JPanel espaceHaut = new JPanel();
	    espaceHaut.setBackground(Color.white);
	    espaceHaut.setPreferredSize(new Dimension(750, 125));
	    
	    
	    //initialisation des valeurs de la base de données
	    String mail = "";
	    String pseudo = "";
	    mailField = new JFormattedTextField(mail);
		pseudoField = new JFormattedTextField(pseudo);
	    
	    
	    //PseudoField
	    Font police = new Font("Arial", Font.BOLD, 14);
		pseudoField.setFont(police);
		pseudoField.setPreferredSize(new Dimension(150, 30));
		pseudoField.setForeground(Color.black);
		
		//MdpField
		mdpField.setFont(police);
		mdpField.setPreferredSize(new Dimension(150, 30));
		mdpField.setForeground(Color.black);
		
		//MailField 
		mailField.setFont(police);
		mailField.setPreferredSize(new Dimension(150, 30));
		mailField.setForeground(Color.black);
		
	    //bloc contenant l'information
	    JPanel infos = new JPanel();
	    infos.setLayout(new GridLayout(3, 2));
	    infos.add(mailLabel);
	    infos.add(mailField);
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
	    
	    // ligne 1 : bloc contenant infos -> boutton de connexion
	    gbc.gridy = 1;
	    this.add(infos,gbc);
	    
	    gbc.gridx = 1;
	    coButton.addActionListener(new BoutonProfileListener());
	    this.add(coButton, gbc);

		this.setVisible(true);
	}
	
	class BoutonProfileListener implements ActionListener {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if ((JButton) e.getSource() == coButton) {
				System.out.println( "Adresse mail : " + mailField.getText() + 
						"Pseudo renseigné : " + pseudoField.getText() + " le mot de passe associé : " + mdpField.getText());
			}
		}
	}
}
