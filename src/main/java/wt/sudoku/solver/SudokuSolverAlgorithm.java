package wt.sudoku.solver;

import wt.sudoku.model.board.main.Board;
import wt.sudoku.model.board.main.Cell;

public interface SudokuSolverAlgorithm {

	public Board solveSudokuBoard(Board enterBoard, int returnLimit);
	public Cell findNextSolution(Board enterBoard);
}
