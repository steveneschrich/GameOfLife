package gameOfLife;

import gameOfLife.UpdateListener;
import java.util.ArrayList;

public class BoardModel {
	private ArrayList<UpdateListener> actionListeners = null;
	private boolean[][] board = null;
	private int width = 0;
	private int height = 0;

	public BoardModel(int height, int width) {
		this.actionListeners = new ArrayList<UpdateListener>(2);
		this.height = height + 2;
		this.width = width + 2;
		this.board = createBoard();
	}
	
	private boolean[][] createBoard() {
		boolean[][] board = new boolean[height][width];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = false;
			}
		}
		return board;
	}

	public void addCoordinate(int x, int y) {
		this.board[x+1][y+1] = true;
		update();
	}

	public void removeCoordinate(int x, int y) {
		this.board[x+1][y+1] = false;
		update();
	}

	public void addUpdateListener(UpdateListener l) {
		actionListeners.add(l);
	}
	
	public void nextGeneration() {
		int i, j;
		boolean newBoard[][] = createBoard();
		
		for (i = 1; i < height-1; i++) {
			for (j = 1; j < width-1; j++) {
				int num = numberOfNeighbors(i, j);
	
				if (num == 3) {
					newBoard[i][j] = true;
				} else if (num < 2) {
					newBoard[i][j] = false;
				} else if (num == 2 && board[i][j] == true) { 
					newBoard[i][j] = true;
				} else if (num > 3) {
					newBoard[i][j] = false;
				}
			}
		}
		board = newBoard;
		update();
	}


	private void update() {
		for (int i = 0; i < actionListeners.size(); i++) {
			boolean[][] cbValue = removePadding(board);
			actionListeners.get(i).updateOccured(cbValue);
		}
	}
	
	private boolean[][] removePadding(boolean[][] board) {
		boolean[][] accum = new boolean[board.length-2][];
		for (int i = 1; i < board.length-1; i++) {
			boolean[] acc = new boolean[board[i].length-2];
			for (int j = 1; j < board[i].length-1; j++) {
				acc[j-1] = board[i][j];
			}
			accum[i-1] = acc;
		}
		return accum;
	}
	private int numberOfNeighbors(int i, int j) {
		int k, l, count = 0;

		for (k = -1; k <= 1; k++) {
			for (l = -1; l <= 1; l++) {
				boolean cell = board[i+k][j+l];
				if (cell) {
					count++;
				}
			}
		}

		if (board[i][j])
			count--;

		return count;
	}

	public void clear() {
		this.board = createBoard();
		update();
		
	}
}