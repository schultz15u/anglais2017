package view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.swing.JButton;
  
@SuppressWarnings("serial")
public class LogoButton extends JButton{
  private Image img;

  public LogoButton(URL url){
    super("");
    this.setPreferredSize(new Dimension(160, 160));
  }

  public void paintComponent(Graphics g){
    Graphics2D g2d = (Graphics2D)g;
    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
  }
}