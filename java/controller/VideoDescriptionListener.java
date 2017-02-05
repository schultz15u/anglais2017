package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.VideoDescription;

public class VideoDescriptionListener implements ActionListener {
	 private VideoDescription videoReference;
	
	public VideoDescriptionListener(VideoDescription video) {
		this.videoReference = video;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		videoReference.afficheVideo();
	}

}
