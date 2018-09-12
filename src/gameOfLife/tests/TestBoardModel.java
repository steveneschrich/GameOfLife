package gameOfLife.tests;

import gameOfLife.BoardModel;
import gameOfLife.UpdateListener;

public class TestBoardModel {
	public static final boolean[][] exampleOne = new boolean[][]{
		{false, false, false, false, false},
		{false, false, false, false, false},
		{false, false, false, false, false},
		{false, false, false, false, false},
		{false, false, false, false, false}
	};
	
	public static void main(String args[]) {
		BoardModel model = new BoardModel(5, 5);
		model.addUpdateListener(new Tester());
		
		exampleOne[2][2] = true;
		model.addCoordinate(2, 2);
		
		exampleOne[2][2] = false;
		model.removeCoordinate(2, 2);
		
		exampleOne[1][3] = true;
		model.addCoordinate(1, 3);
		
		System.out.println("pass");
	}
	
	public static class Tester implements UpdateListener {
		boolean active = false;
		public void updateOccured(boolean[][] board) {
			assert board.length == exampleOne.length;
			assert board[0].length == exampleOne[0].length;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					System.out.println(board[i][j] + " " + exampleOne[i][j]);
					assert board[i][j] == exampleOne[i][j];
				}
			}	
		}
	}
}
