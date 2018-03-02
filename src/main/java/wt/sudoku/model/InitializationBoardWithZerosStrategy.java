package wt.sudoku.model;

import wt.sudoku.model.board.main.Board;
import wt.sudoku.model.board.main.Cell;
import wt.sudoku.model.elements.container.CellParams;
import wt.sudoku.model.enums.Col3PairType;
import wt.sudoku.model.enums.ColumnType;
import wt.sudoku.model.enums.Rect3X3Type;
import wt.sudoku.model.enums.Row3PairType;
import wt.sudoku.model.enums.RowType;

public class InitializationBoardWithZerosStrategy implements InitializationBoardStrategy{

	@Override
	public void initializeBoard(Board board) {
			for (int x = 0; x < board.getSudokuCells().length; x++) {
				for (int y = 0; y < board.getSudokuCells()[x].length; y++) {
					CellParams cellParams = new CellParams(x, y, Rect3X3Type.calculateRectType(x, y),
							ColumnType.calculateColumnType(x), RowType.calculateRowType(y),
							Col3PairType.calculateCol3Type(x, y), Row3PairType.calculateRow3Type(x, y));
					board.getSudokuCells()[x][y] = new Cell(cellParams, board.getBoardMap());
					
				}
			}
		}
	
}
