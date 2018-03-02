package wt.sudoku.utils;

import wt.sudoku.model.board.main.Cell;

public class SudokuBoardTextPrinter {

	public static void printSudokuBoard(Cell[][] sudokuBoard) {
		int size = 0;
		for (int y=0; y<sudokuBoard.length; y++) {
			for (int x=0; x<sudokuBoard[y].length; x++) {
				if (sudokuBoard[x][y].getValue() != 0) size++;
				if (x == 2 || x == 5) System.out.print(sudokuBoard[x][y].getValue() + " || ");
				else if (x == 8) System.out.print(sudokuBoard[x][y].getValue());
				else System.out.print(sudokuBoard[x][y].getValue()+" | ");
				
			}
			System.out.println();
			if (y == 2 || y == 5)
			System.out.println("-----------------------------------");
			System.out.println("-----------------------------------");
			
		}
		System.out.println("size : "+size);
	}
	
	
}
