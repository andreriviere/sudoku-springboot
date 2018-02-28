package wt.sudoku.model;

public interface BoardPlay {

	Cell[][] getSudokuCells();

	boolean isBoardSolvable();

	boolean isValueValidToAdd(int x, int y, int value);

	void deleteValue(int x, int y, int value);

	void addValueToCell(int x, int y, int value);

}
