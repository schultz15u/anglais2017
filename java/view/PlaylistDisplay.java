package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.PlaylistDeleter;
import controller.PlaylistModifier;
import model.object.CollectionModel;

@SuppressWarnings("serial")
public class PlaylistDisplay extends JPanel {

	public PlaylistDisplay(CollectionModel p){
		JLabel playlistTitle = new JLabel();
		playlistTitle.setText(p.getNameCollection());
		NameButton b = new NameButton(null);
		b.add(playlistTitle);
		b.addActionListener(new PlaylistModifier());
		add(b);
		JButton m = new JButton("Modifier");
		m.addActionListener(new PlaylistModifier());
		add(m);
		JButton s = new JButton("Supprimer");
		s.addActionListener(new PlaylistDeleter(p));
		add(s);
		/*for (VideoModel v : p.){
			try {
				p.getVideos().add(new VideoDescriptionDisplay(v));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		//add(p.getVideos());
	}
	
}
