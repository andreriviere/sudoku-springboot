package generator;


import org.junit.Test;

import model.Board;
import utils.SudokuBoardTextPrinter;

public class SudokuBoardGeneratorTest {

	@Test
	public void generateRandomSudokuBoardTest() {
		SudokuBoardGenerator sudokuBoardGenerator = new LowLevelSudokuGenerator();
		Board board = null;
		int repatTime = 1;
		int maxSize = 0;
		int size = 0;
		Board bestBoard = null;

		for (int i=0; i<repatTime; i++)
		{
		board = sudokuBoardGenerator.generateSolvableBoard();
		size = 0;
		for (int y=0; y<board.getSudokuCells().length; y++) 
			for (int x=0; x<board.getSudokuCells()[y].length; x++) 
				if (board.getSudokuCells()[x][y].getValue() != 0) size++;
		if (size > maxSize) {
			maxSize = size;
			bestBoard = board;
		}
		}
		
		System.out.println("maxsize : "+maxSize);
		SudokuBoardTextPrinter.printSudokuBoard(bestBoard.getSudokuCells());
	}

}
