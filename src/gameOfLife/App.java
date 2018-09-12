

package gameOfLife;

import gameOfLife.Board;
import gameOfLife.ButtonPanel;
import gameOfLife.DoneCallback;
import gameOfLife.SizeSelect;
import gameOfLife.GenerationCounter;
import gameOfLife.BoardModel;


import javax.swing.*;

import java.awt.BorderLayout;

public class App extends JFrame {
	private static final long serialVersionUID = 3952579601767054319L;
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 500;

	private Board board = null;
	private BoardModel model = null;

	public App(int height, int width) {
		super("Game of Life");
		if (width*height > 2500 || width > 50 || height > 50) {
			App.main(new String[]{});
			return;
		}
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		model = new BoardModel(height, width);

		board = new Board(height, width, model);
		ButtonPanel bp = new ButtonPanel(model);
		model.addUpdateListener(board);
		
		GenerationCounter g = new GenerationCounter(0);
		bp.addTimerListener(g);

		bp.add(g, 0, 0);
		add(bp, BorderLayout.EAST);
		add(board, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		SizeSelect s = new SizeSelect(new AppStarter());
		s.setVisible(true);
	}

	private static class AppStarter implements DoneCallback {
		public void done(int height, int width) {
			App app = new App(height, width);
			app.setVisible(true);

		}
	}
}