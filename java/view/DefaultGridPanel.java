package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;

import view.customized_widgets.CustomizedPanel;

public abstract class DefaultGridPanel extends CustomizedPanel {

	private Insets insets = new Insets(0, 0, 0, 0);

	public DefaultGridPanel() {

		super();
		setLayout(new GridBagLayout());
	}

	protected void addComponent(JComponent component, int gridX, int gridY, int sizeX, int sizeY, double weightX,
			double weightY, int anchor, int fill) {

		GridBagConstraints gbc = new GridBagConstraints(gridX, gridY, sizeX, sizeY, weightX, weightY, anchor, fill,
				insets, 0, 0);

		add(component, gbc);
	}

	protected void addComponent(JComponent component, int gridX, int gridY, int sizeX, int sizeY, double weightX,
			double weightY) {
		addComponent(component, gridX, gridY, sizeX, sizeY, weightX, weightY, GridBagConstraints.BASELINE,
				GridBagConstraints.NONE);
	}

	protected void addComponent(JComponent component, int gridX, int gridY, int sizeX, int sizeY) {
		addComponent(component, gridX, gridY, sizeX, sizeY, 1, 1, GridBagConstraints.BASELINE, GridBagConstraints.NONE);
	}
}
