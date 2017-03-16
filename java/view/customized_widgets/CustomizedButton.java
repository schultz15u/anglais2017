package view.customized_widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import view.StyleParameters;

public class CustomizedButton extends JButton {

	private Color normalColor;
	private Color selectedColor;
	private Color clickedColor;

	private Color oldBackgroundColor;
	private Color newBackgroundColor;
	private Timer timer;
	private long startTime;
	private int state;  // 0 : normal, 1 : selected, 2 : clicked
	private boolean cursorIsOut;
	
	private static final int DELAY = 1;
	private static final double ANIMATION_TIME = 200;
	
	public CustomizedButton(String text){
		super(text);

		normalColor = StyleParameters.defaultWidgetBackgroundColor;
		selectedColor = StyleParameters.defaultSelectedWidgetBackgroundColor;
		clickedColor = StyleParameters.defaultClickedWidgetBackgroundColor;
		
		oldBackgroundColor = normalColor;
		newBackgroundColor = oldBackgroundColor;
		startTime = 0;
		state = 0;
		cursorIsOut = true;

		setBackground(newBackgroundColor);
		setForeground(StyleParameters.defaultTextColor);
		setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
		setFont(StyleParameters.defaultImportantFont);
		addMouseListener(new ButtonListener());
		setBorderPainted(false);
		
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

	public void setNormalColor(Color color) {

		normalColor = color;

		if (state == 0) {

			oldBackgroundColor = color;
			newBackgroundColor = color;
			setBackground(color);
		}
	}

	public void setSelectedColor(Color color) {

		selectedColor = color;

		if (state == 1) {

			oldBackgroundColor = color;
			newBackgroundColor = color;
			setBackground(color);
		}
	}

	public void setClickedColor(Color color) {

		clickedColor = color;

		if (state == 2) {

			oldBackgroundColor = color;
			newBackgroundColor = color;
			setBackground(color);
		}
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
	
	class ButtonListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			state = 1;
			stopTimer();
			newBackgroundColor = selectedColor;
			startTimer();
			cursorIsOut = false;
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			state = 0;
			stopTimer();
			newBackgroundColor = normalColor;
			startTimer();
			cursorIsOut = true;
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			state = 2;
			stopTimer();
			newBackgroundColor = clickedColor;
			startTimer();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

			stopTimer();

			if (cursorIsOut) {
				state = 0;
				newBackgroundColor = normalColor;
			}
			else {
				state = 1;
				newBackgroundColor = selectedColor;
			}

			startTimer();
		}
	}
}
