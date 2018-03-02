package wt.sudoku.model;

import static org.junit.Assert.*;

import org.junit.Test;

import wt.sudoku.model.board.main.Board;
import wt.sudoku.utils.SudokuBoardHelper;

public class BoardPlayTest {


	public void isAddToBoardWorkingUsingSolvableBoardTest() {
		int[][] solvableBoard = { 
				{0,0,0, 0,0,8, 5,0,3},
				{4,9,7, 3,0,0, 2,0,8},
				{0,5,0, 2,0,1, 0,0,7},
				
				{0,0,0, 1,0,5, 0,0,0},
				{0,1,8, 9,0,3, 0,0,6},
				{2,0,0, 0,6,7, 0,0,0},
				
				{1,6,5, 7,8,0, 3,0,2},
				{0,7,4, 0,0,2, 0,5,0},
				{9,0,2, 0,3,0, 6,7,1},
			};
		BoardPlay boardPlay = SudokuBoardHelper.getBoard(solvableBoard);

		boolean isOk = true;
		for (int x=0; x<solvableBoard.length; x++) {
			for (int y=0; y<solvableBoard.length; y++) {
				if (boardPlay.getSudokuCells()[x][y].getValue()!=solvableBoard[x][y]) isOk = false;
			}
		}
		assertTrue(isOk);
		
	}
	
	@Test
	public void testIfPossibilitiesWork() throws Exception {
		int[][] boardCells = { 
				{1,6,2, 3,4,0, 8,9,7},
				{4,9,3, 8,6,7, 1,2,5},
				{7,8,5, 9,2,1, 6,3,4},
				
				{6,2,7, 5,0,0, 0,4,0},
				{5,1,8, 7,0,9, 0,6,0},
				{3,4,9, 6,0,2, 7,5,0},
				
				{2,0,1, 4,0,6, 0,0,9},
				{8,0,6, 0,0,0, 0,0,0},
				{9,5,4, 0,0,0, 6,0,0},
			};
		Board board = SudokuBoardHelper.getBoard(boardCells);
		board.fillListWithValidValuesPerEachCell();

		assertTrue(true);
	}

}
