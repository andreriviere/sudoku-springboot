package wt.sudoku.utils;

import wt.sudoku.model.BoardValidationWithBackupStrategy;
import wt.sudoku.model.InitializationBoardWithZerosStrategy;
import wt.sudoku.model.board.main.Board;

public class SudokuBoardHelper {

	public static Board getBoard(int[][] solvableBoard) {
		Board board = new Board(new InitializationBoardWithZerosStrategy(), new BoardValidationWithBackupStrategy());
		for (int x=0; x<solvableBoard.length; x++) {
			for (int y=0; y<solvableBoard.length; y++) {
				board.addValueToCell(x, y, solvableBoard[x][y]);
			}
		}
		return board;
	}
}
