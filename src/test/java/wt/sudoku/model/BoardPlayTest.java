package wt.sudoku.model;

import static org.junit.Assert.*;

import org.junit.Test;

import wt.sudoku.model.board.main.Board;
import wt.sudoku.solver.SudokuSolverUsingReturnAlgorithm;
import wt.sudoku.utils.SudokuBoardHelper;
import wt.sudoku.utils.SudokuBoardTextPrinter;

public class BoardPlayTest {

	@Test
	public void testIfFindNextSolutionWork() throws Exception {
//		int[][] easySolvableBoard = { 
//				{0,0,0, 0,0,8, 5,0,3},
//				{4,9,7, 3,0,0, 2,0,8},
//				{0,5,0, 2,0,1, 0,0,7},
//				
//				{0,0,0, 1,0,5, 0,0,0},
//				{0,1,8, 9,0,3, 0,0,6},
//				{2,0,0, 0,6,7, 0,0,0},
//				
//				{1,6,5, 7,8,0, 3,0,2},
//				{0,7,4, 0,0,2, 0,5,0},
//				{9,0,2, 0,3,0, 6,7,1},
//			};
		
//		int[][] hardSolvableBoard = {
//				{0,0,0, 0,0,3, 0,0,5},
//				{6,5,0, 0,0,9, 0,0,0},
//				{0,8,0, 0,6,5, 4,0,0},
//				
//				{0,2,0, 0,0,4, 1,0,0},
//				{0,0,0, 0,0,0, 0,0,0},
//				{0,0,4, 7,0,0, 0,6,0},
//				
//				{0,0,2, 6,8,7, 0,1,0},
//				{0,0,0, 4,0,1, 0,2,7},
//				{7,0,0, 3,0,2, 0,0,0}
//		};
		int[][] hardSolvableBoard = {
		//           0 1 2  3 4 5  6 7 8
		/*0 */   	{0,0,0, 1,0,3, 0,0,5},
		/*1 */		{6,5,0, 0,0,9, 0,0,0},
		/*2 */		{0,8,0, 2,6,5, 4,0,0},
				
		/*3 */		{0,2,0, 0,3,4, 1,0,0},
		/*4 */		{0,0,0, 0,0,6, 0,0,0},
		/*5 */		{0,0,4, 7,0,8, 0,6,0},
				
		/*6 */		{0,0,2, 6,8,7, 0,1,0},
		/*7 */		{0,0,0, 4,0,1, 0,2,7},
		/*8 */		{7,0,0, 3,0,2, 0,0,6}
		};
		Board board = SudokuBoardHelper.getBoard(hardSolvableBoard, new SudokuSolverUsingReturnAlgorithm());
		SudokuBoardTextPrinter.printSudokuBoard(board.getSudokuCells());
		assertTrue(board.isBoardSolvable());
	}
}
