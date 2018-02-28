
package wt.sudoku.generator;

public enum SudokuLevel {
	EASY(20), MEDIUM(30), HARD(40);

	private int emptyCount;

	SudokuLevel(int emptyCount) {
		this.emptyCount = emptyCount;
	}

	public int getEmptyCount() {
		return emptyCount;
	}

}
