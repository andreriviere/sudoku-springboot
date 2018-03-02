package generator;

import org.junit.Test;

import wt.sudoku.generator.GroupFillingSudokuGenerator;
import wt.sudoku.generator.SudokuBoardGenerator;
import wt.sudoku.generator.SudokuLevel;
import wt.sudoku.model.BoardPlay;
import wt.sudoku.utils.SudokuBoardTextPrinter;

public class SudokuBoardGeneratorTest {

//	@Test
	public void generateRandomSudokuBoardTest() {
		SudokuBoardGenerator sudokuBoardGenerator = new GroupFillingSudokuGenerator();
		BoardPlay board = null;
		int repatTime = 1;
		int maxSize = 0;
		int size = 0;
		BoardPlay bestBoard = null;

		for (int i = 0; i < repatTime; i++) {
			board = sudokuBoardGenerator.generateSolvableBoard(SudokuLevel.HARD);
			size = 0;
			for (int y = 0; y < board.getSudokuCells().length; y++)
				for (int x = 0; x < board.getSudokuCells()[y].length; x++)
					if (board.getSudokuCells()[x][y].getValue() != 0)
						size++;
			if (size > maxSize) {
				maxSize = size;
				bestBoard = board;
			}
		}

		SudokuBoardTextPrinter.printSudokuBoard(bestBoard.getSudokuCells());
	}

}
