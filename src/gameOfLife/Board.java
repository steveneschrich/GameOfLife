package gameOfLife;

import gameOfLife.Cell;
import gameOfLife.BoardModel;
import gameOfLife.UpdateListener;

import javax.swing.*;
import java.awt.GridLayout;

public class Board extends JPanel implements UpdateListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6276267085494637299L;
	private Cell[][] board = null;

	public Board(int height, int width, BoardModel model) {
		super();
		setLayout(new GridLayout(height, width)); 
		
		board = new Cell[height][width]; 
		int i, j;

		for (i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {
				board[i][j] = new Cell(i, j, model);
				add(board[i][j]);
			}
		}
	}

	public void clear() {
		int i, j;
		for (i = 0; i < board.length; i++) {
			for (j = 0; j < board[i].length; j++) {
				board[i][j].setState(false);
			}
		}
	}

	private void applyBoard(boolean[][] state) {
		clear();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j].setState(state[i][j]);;
			}
		}
	}

	public void updateOccured(boolean[][] board) {
		applyBoard(board);
	}
}