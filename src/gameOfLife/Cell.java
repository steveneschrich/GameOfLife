package gameOfLife;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton implements ActionListener {
	private static final long serialVersionUID = -9124491155084761227L;

	private static final Border blackline = 
		BorderFactory.createLineBorder(Color.BLACK);

	private static final Border whiteline = 
		BorderFactory.createLineBorder(Color.WHITE);

	private boolean state = false;
	private BoardModel model = null;
	private int x, y;
	public Cell(boolean state, int x, int y, BoardModel model) { 
		super();
		addActionListener(this);
		// setText(coord.toString());
		setText("");
		setBorder(blackline);
		setBackground(Color.WHITE);
		this.x = x;
		this.y = y;
		this.model = model;
		this.state = state;
	}
	public Cell(int x, int y, BoardModel model) { 
		this(false, x, y, model); 
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
		if (state) {
			setBackground(Color.BLACK);
			setBorder(whiteline);
		} else {
			setBackground(Color.WHITE);
			setBorder(blackline);
			
		}
	}

	public void actionPerformed(ActionEvent e) {
		setState(!state);
		if (state)
			model.addCoordinate(x, y);
		else {
			model.removeCoordinate(x, y); 
		}
	}
}