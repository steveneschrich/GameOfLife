package gameOfLife;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import gameOfLife.BoardModel;

public class ButtonPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 6551723488754564117L;
	private Timer timer = null;
	private BoardModel board = null;
	private static final int DELAY = (int)(0.5 * 1000);

	public ButtonPanel(BoardModel board) {
		super();

		timer = new Timer(DELAY, this);
		timer.setActionCommand("inc");
		this.board = board;
		setLayout(new GridLayout(5, 1));

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new StartStopHandler(startButton));
		

		JButton next = new JButton("Next Generation");
		next.addActionListener(new NextButtonHandler());

		JButton clearButton = new JButton("Clear Board");
		clearButton.addActionListener(new ClearButtonHandler());

		add(startButton);
		add(next);
		add(clearButton);
	}

	private class StartStopHandler implements ActionListener {
		private JButton b = null;
		StartStopHandler(JButton b) {
			this.b = b;
		}
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Start")) {
				timer.start();
				b.setText("Stop");
				timer.setActionCommand("inc");
			} else if (e.getActionCommand().equals("Stop")) {
				timer.stop();
				b.setText("Start");
			}	
		}
	}

	private class ClearButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			board.clear();
			ActionListener g = getCounter();
			if (g != null)
				g.actionPerformed(new ActionEvent(board, 0, "clear"));
			
		}
	}
	
	private ActionListener getCounter() {
		GenerationCounter g = new GenerationCounter(0);
		for (ActionListener l : timer.getActionListeners()) {
			if (((Object) l).getClass()
					.equals(g.getClass())) {
				return l;
			}
		}
		return null;
	}
	private class NextButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ActionListener g = getCounter();
			if (g != null)
				g.actionPerformed(new ActionEvent(board, 0, "inc"));
			board.nextGeneration();
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		board.nextGeneration();
	}

	public void addTimerListener(ActionListener g) {
		timer.addActionListener(g);
	}
}
