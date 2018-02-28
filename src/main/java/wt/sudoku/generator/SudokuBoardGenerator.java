package wt.sudoku.generator;


import wt.sudoku.model.BoardPlay;

public interface SudokuBoardGenerator {

	public BoardPlay generateSolvableBoard(SudokuLevel sudokuLevel);
}
