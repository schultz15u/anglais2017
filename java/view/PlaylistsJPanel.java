package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import model.PlaylistCreation;
import model.object.CollectionModel;

/**
 * Window that displays the playlists
 * @author Menou
 *	not finished
 */
@SuppressWarnings("serial")
public class PlaylistsJPanel extends JPanel{
	
	private PlaylistCreation playlistCreationBar;
	private GridBagConstraints gbc;
	//private PlaylistDisplay playlists;
	
	public PlaylistsJPanel(MainScreen screen){
		gbc = new GridBagConstraints();
		this.setPreferredSize(new Dimension(750, 500));
	    //this.setLayout(new GridLayout(screen.getUser().getAllPlaylists().size(), 1));
		
	    playlistCreationBar = new PlaylistCreation(screen);
	    
		PlaylistCreationDisplay barreCreation = new PlaylistCreationDisplay(playlistCreationBar);
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    add(barreCreation);
				
		for (CollectionModel c : screen.getMyPlaylists()){
			if (!c.getNameCollection().equals("Abonnements")){
				add(new PlaylistDisplay(c));
				gbc.gridx = 0;
			    gbc.gridy += gbc.gridy;
			    gbc.gridheight = 1;
			    gbc.gridwidth = 1;
			}
		}
	}
	
	public PlaylistCreation getCreationBar(){
		return playlistCreationBar;
	}
}
