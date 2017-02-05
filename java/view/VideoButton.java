package view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
  
@SuppressWarnings("serial")
public class VideoButton extends JButton{
  private Image img;

  public VideoButton(String str){
    super(str);
    this.setPreferredSize(new Dimension(120, 90));
    try {
    	img = ImageIO.read((new URL(str)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void paintComponent(Graphics g){
    Graphics2D g2d = (Graphics2D)g;
    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
  }
}