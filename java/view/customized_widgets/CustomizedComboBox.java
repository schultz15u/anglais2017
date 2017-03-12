package view.customized_widgets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomizedComboBox extends JComboBox {

	public CustomizedComboBox(Object[] items){
		super(items);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(70, 70, 70));
	}
}
