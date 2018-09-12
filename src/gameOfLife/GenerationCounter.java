package gameOfLife;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerationCounter extends JPanel implements ActionListener {
	private static final long serialVersionUID = -5837986313427913693L;
	int count = 0;
	JLabel label = null;
	GenerationCounter(int num) {
		label = new JLabel();
		add(label);
		setCount(num);
	}
	private void setCount(int num) {
		this.count = num;
		label.setText("Generation " + getCount());
	}

	public int getCount() {
		return count;
	}

	public void increment() {
		setCount(count+1);
	}

	public void decrement() {
		setCount(count-1);
	}

	public void clear() {
		setCount(0);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("clear")) {
			clear();
		} else {
			increment();
		}
		
	}
}