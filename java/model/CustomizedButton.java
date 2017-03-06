package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class CustomizedButton extends JButton
{
	public CustomizedButton(String text)
	{
		super(text);
		setPreferredSize(new Dimension(200, 50));
		setBackground(Color.blue);
		setForeground(Color.white);
		setFocusPainted(false);
		setFont(new Font("Arial", Font.BOLD, 20));
	}
}
