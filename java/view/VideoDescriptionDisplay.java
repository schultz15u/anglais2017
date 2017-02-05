package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.VideoDescriptionListener;
import model.VideoDescription;

@SuppressWarnings("serial")
public class VideoDescriptionDisplay extends JPanel {
	VideoDescription video;
	JLabel labelDate;
	JLabel labelDescription;
	private GridBagConstraints gbc;
		
	public VideoDescriptionDisplay(VideoDescription video) throws Exception {
		this.video = video;
		
		this.setLayout(new GridBagLayout()); // objet pour le positionnement
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		this.setBackground(Color.WHITE);
		
		if (!video.getThumbnail().equals("")){
			
			String url = video.getThumbnail();
			
			VideoButton boutonImg = new VideoButton(url);
			boutonImg.addActionListener(new VideoDescriptionListener(video));
			
			this.add(boutonImg,gbc);
			
			gbc.gridx = 1;
			gbc.gridheight = 1;
			gbc.gridwidth = 2;
			NameButton boutonName = new NameButton(video.getvideoName());
			JLabel name = new JLabel(video.getvideoName());
			Font police = new Font("Tahoma", Font.BOLD, 16);
			name.setFont(police);
			boutonName.add(name);
			boutonName.setBorderPainted(false);
			
			
			boutonName.addActionListener(new VideoDescriptionListener(video));
			boutonName.setLayout(new FlowLayout(FlowLayout.LEFT));
			

			this.add(boutonName, gbc);
			
			String date = video.getDate();
			labelDate = new JLabel(date);
			labelDate.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			this.add(labelDate,gbc);
			
			labelDescription = new JLabel(""+video.getDescription());
			labelDescription.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			gbc.gridy = 2;
			gbc.gridwidth = 3;
			this.add(labelDescription,gbc);
			
		} else {// cas du "les 25 premiers résultats pour le query"
			JTextField name = new JTextField(video.getvideoName());
			name.setEditable(false);
			this.add(name);
		}

	}
	
}
