package wt.sudoku.model;

import wt.sudoku.model.board.main.Cell;

public interface BoardPlay {

	Cell[][] getSudokuCells();

	boolean isValueValidToAdd(int x, int y, int value);

	Cell findNextSolution();

	void deleteValue(int x, int y);

	void addValueToCell(int x, int y, int value);

	void cleanHistory();

	boolean isBoardSolvable();

}
