package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.Timer;

import controller.DefaultMouseListener;

public class CustomizedButton extends JButton {
	
	private Color oldBackgroundColor;
	private Color newBackgroundColor;
	private Timer timer;
	private long startTime;
	
	private static final int DELAY = 1;
	private static final double ANIMATION_TIME = 200;
	
	public CustomizedButton(String text){
		super(text);
		
		oldBackgroundColor = Color.black;
		newBackgroundColor = oldBackgroundColor;
		startTime = 0;
		
		setPreferredSize(new Dimension(200, 50));
		setBackground(newBackgroundColor);
		setForeground(new Color(255, 255, 255));
		setFocusPainted(false);
		setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
		setFont(new Font("Arial", Font.BOLD, 20));
		addMouseListener(new ButtonListener());
		
		timer = new Timer(DELAY, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				long time = (System.nanoTime() - startTime) / 1000000;
				int r = (int)getGrayLevel(0, oldBackgroundColor.getRed(), ANIMATION_TIME, newBackgroundColor.getRed(), time);
				int g = (int)getGrayLevel(0, oldBackgroundColor.getGreen(), ANIMATION_TIME, newBackgroundColor.getGreen(), time);
				int b = (int)getGrayLevel(0, oldBackgroundColor.getBlue(), ANIMATION_TIME, newBackgroundColor.getBlue(), time);
				
				setBackground(new Color(Math.max(Math.min(r, 255), 0), Math.max(Math.min(g, 255), 0), Math.max(Math.min(b, 255), 0)));
				repaint();
				
				if (oldBackgroundColor.getRed() > newBackgroundColor.getRed() && getBackground().getRed() <= newBackgroundColor.getRed()
					|| oldBackgroundColor.getRed() < newBackgroundColor.getRed() && getBackground().getRed() >= newBackgroundColor.getRed()
					|| oldBackgroundColor.getGreen() > newBackgroundColor.getGreen() && getBackground().getGreen() <= newBackgroundColor.getGreen()
					|| oldBackgroundColor.getGreen() < newBackgroundColor.getGreen() && getBackground().getGreen() >= newBackgroundColor.getGreen()
					|| oldBackgroundColor.getBlue() > newBackgroundColor.getBlue() && getBackground().getBlue() <= newBackgroundColor.getBlue()
					|| oldBackgroundColor.getBlue() < newBackgroundColor.getBlue() && getBackground().getBlue() >= newBackgroundColor.getBlue())
				{
					oldBackgroundColor = newBackgroundColor;
					stopTimer();
				}                
            }
			
			private double getGrayLevel(double time1, double grayLevel1, double time2, double grayLevel2, double time) {
				double A = (grayLevel2 - grayLevel1) / (time2 - time1);
				double B = grayLevel1 - A * time1;
				
				return A * time + B;
			}
        });
	}
	
	private void startTimer() {
		startTime = System.nanoTime();
		timer.stop();
		timer.start();
	}
	
	private void stopTimer() {
		timer.stop();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
	
	class ButtonListener extends DefaultMouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			newBackgroundColor = new Color(0, 128, 255);
			startTimer();
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			newBackgroundColor = Color.black;
			startTimer();
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			newBackgroundColor = Color.cyan;
			startTimer();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			newBackgroundColor = new Color(0, 128, 255);
			startTimer();
		}
		
	}
}
