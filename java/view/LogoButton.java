package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LogoButton extends JButton {

	public LogoButton(MainScreen screen, BufferedImage im) {
		super(new ImageIcon(im));
		/*
		 * this.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * screen.update(tmp, null); } });
		 */
		this.setPreferredSize(new Dimension(160, 160));
	}

}