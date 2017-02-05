package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
  
@SuppressWarnings("serial")
public class NameButton extends JButton implements MouseListener{
	
	private Graphics2D g2d;
	private Color color;
	@SuppressWarnings("unused")
	private String primalLabel;

	@SuppressWarnings("deprecation")
	public NameButton(String str) {
		super(str);
		primalLabel = this.getLabel();
	    //Grâce à cette instruction, notre objet va s'écouter
	    //Dès qu'un événement de la souris sera intercepté, il en sera averti
	    this.addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		this.g2d = (Graphics2D)g;
		color = Color.WHITE;
		g2d.setColor(color);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		

	    //this.g2d = (Graphics2D)g;
	    //g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
		public void mouseClicked(MouseEvent e){
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			//primalLabel = this.getLabel();
			this.setFont(new Font("Tahoma", Font.ITALIC, 16));
			//this.setLabel("<html><u>"+primalLabel+"</u></html>");
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			this.setFont(new Font("Tahoma", Font.BOLD, 16));
			//setLabel(primalLabel);
			repaint();
		}
	
}