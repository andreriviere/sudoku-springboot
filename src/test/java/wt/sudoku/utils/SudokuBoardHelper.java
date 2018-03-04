package wt.sudoku.utils;

import wt.sudoku.model.BoardValidationWithBackupStrategy;
import wt.sudoku.model.InitializationBoardWithZerosStrategy;
import wt.sudoku.model.board.main.Board;
import wt.sudoku.solver.SudokuSolverAlgorithm;

public class SudokuBoardHelper {

	public static Board getBoard(int[][] solvableBoard, SudokuSolverAlgorithm sudokuSolverAlgorithm) {
		Board board = new Board(new InitializationBoardWithZerosStrategy(), new BoardValidationWithBackupStrategy(), sudokuSolverAlgorithm);
		for (int y=0; y<solvableBoard.length; y++) {
			for (int x=0; x<solvableBoard.length; x++) {
				board.addValueToCell(x, y, solvableBoard[y][x]);
			}
		}
		return board;
	}
}
