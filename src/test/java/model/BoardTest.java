package model;

import static org.junit.Assert.*;

import org.junit.Test;

import wt.sudoku.model.Board;
import wt.sudoku.model.enums.ColumnType;
import wt.sudoku.model.enums.Rect3X3Type;
import wt.sudoku.model.enums.RowType;

public class BoardTest {

	@Test
	public void isBoardMapInitializedCorrectlyTest() {
		Board board = new Board();
		assertTrue(board.getBoardMap().getColumns().getColumns().size() == 9);
		assertTrue(board.getBoardMap().getRects().getRects().size() == 9);
		assertTrue(board.getBoardMap().getRows().getRows().size() == 9);
	}
	
	@Test
	public void isAddSomeValuesWorkingTest() {
		Board board = new Board();
		board.addValueToCell(0, 0, 8);
		board.addValueToCell(0, 1, 7);
		board.addValueToCell(0, 2, 6);
		assertTrue(board.getBoardMap().getColumns().getColumns().get(ColumnType.ColumnA).size() == 3);
		assertTrue(board.getBoardMap().getRects().getRects().get(Rect3X3Type.TOP_LEFT).size() == 3);
		assertTrue(board.getBoardMap().getRows().getRows().get(RowType.ROW1).size() == 1);
	}
	
	@Test
	public void isHistoryOfAddingValuesWorkingTest() {
		Board board = new Board();
		board.addValueToCell(0, 0, 8);
		board.addValueToCell(0, 1, 7);
		board.addValueToCell(0, 2, 6);
		assertTrue(board.getAddValueHistory().size() == 3);
	}

}
