package wt.sudoku.utils;

import wt.sudoku.model.board.main.Board;

public class BoardStatisticsHelper {

	public static int calculateFilledFields(Board board) {
		int size = 0;
			for (int y = 0; y < board.getSudokuCells().length; y++)
				for (int x = 0; x < board.getSudokuCells()[y].length; x++)
					if (board.getSudokuCells()[x][y].getValue() != 0)
						size++;

		return size;
	}
}
