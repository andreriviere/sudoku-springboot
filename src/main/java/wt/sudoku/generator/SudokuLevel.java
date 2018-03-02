
package wt.sudoku.generator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import wt.sudoku.model.board.main.Board;
import wt.sudoku.model.board.main.Cell;

public enum SudokuLevel {
	EASY(30), MEDIUM(40), HARD(50);

	private int emptyCount;
	private Random randomGenerator;

	SudokuLevel(int emptyCount) {
		this.emptyCount = emptyCount;
		this.randomGenerator = new Random();
	}

	public int getEmptyCount() {
		return emptyCount;
	}

	public Board adjustBoardToSudokuLevel(Board board) {
		Set<Cell> cellsToDelete = new HashSet<Cell>();
		for (int i = 0; i < emptyCount; ) {
			int randomX = randomGenerator.nextInt(9);
			int randomY = randomGenerator.nextInt(9);
			Cell cell = board.getSudokuCells()[randomX][randomY];
			if (cell.getValue() != 0 && !cellsToDelete.contains(cell)) 
			{
				cellsToDelete.add(cell);
				i++;
			}
		}
		
		for (Cell cell : cellsToDelete) {
			board.deleteValue(cell.getCellParams().getX(), cell.getCellParams().getY());
		}
		
		return board;
	}

}
