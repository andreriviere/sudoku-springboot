package wt.sudoku.model;

import static org.junit.Assert.*;

import org.junit.Test;

import wt.sudoku.model.board.main.Board;
import wt.sudoku.solver.SudokuSolverUsingBruteForceAlgorithm;
import wt.sudoku.utils.SudokuBoardHelper;
import wt.sudoku.utils.SudokuBoardTextPrinter;

public class BoardTest {

	@Test
	public void addAvailableValueToCellTest() {

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
		Board board = SudokuBoardHelper.getBoard(solvableBoard, new SudokuSolverUsingBruteForceAlgorithm());
		board.addValueToCell(0, 7, 3);
		assertTrue(board.getSudokuCells()[0][7].getValue() == 3);
	}
	
	@Test
	public void addUnAvailableValueToCellTest() {

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
		Board board = SudokuBoardHelper.getBoard(solvableBoard, new SudokuSolverUsingBruteForceAlgorithm());
		board.addValueToCell(0, 7, 4);
		assertTrue(board.getSudokuCells()[0][7].getValue() == 4);
	}

	@Test
	public void deleteValueTest() {
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
		Board board = SudokuBoardHelper.getBoard(solvableBoard, new SudokuSolverUsingBruteForceAlgorithm());
		board.deleteValue(0, 8);
		assertTrue(board.getSudokuCells()[0][8].getValue() == 0);
	}
//
	@Test
	public void isValueValidToAddTest() {
		
		int[][] solvedBoard = { 
				{6,2,1, 4,7,8, 5,9,3},
				{4,9,7, 3,5,6, 2,1,8},
				{8,5,3, 2,9,1, 4,6,7},
				
				{7,3,6, 1,2,5, 9,8,4},
				{5,1,8, 9,4,3, 7,2,6},
				{2,4,9, 8,6,7, 1,3,5},
				
				{1,6,5, 7,8,9, 3,4,2},
				{3,7,4, 6,1,2, 8,5,9},
				{9,8,2, 5,3,4, 6,7,1},
		};
		
		boolean isOk = true;
		for (int x=0; x<solvedBoard.length; x++) {
			for (int y=0; y<solvedBoard.length; y++) {
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
				Board board = SudokuBoardHelper.getBoard(solvableBoard, new SudokuSolverUsingBruteForceAlgorithm());
				if (board.getSudokuCells()[x][y].getValue() == 0) {
					if (!board.isValueValidToAdd(x, y, solvedBoard[x][y])) {
						System.out.println("x:"+x+" y:"+y+" value:"+solvedBoard[x][y]);
						isOk = false;
					}
				}
			}
		}
		assertTrue(isOk);
	}
	
	@Test
	public void isValueNotValidToAddTest() {
		
		int[][] solvedBoard = { 
				{6,2,1, 4,7,8, 5,9,3},
				{4,9,7, 3,5,6, 2,1,8},
				{8,5,3, 2,9,1, 4,6,7},
				
				{7,3,6, 1,2,5, 9,8,4},
				{5,1,8, 9,4,3, 7,2,6},
				{2,4,9, 8,6,7, 1,3,5},
				
				{1,6,5, 7,8,9, 3,4,2},
				{3,7,4, 6,1,2, 8,5,9},
				{9,8,2, 5,3,5, 6,7,1},
		};
		
		boolean isOk = true;
		for (int x=0; x<solvedBoard.length; x++) {
			for (int y=0; y<solvedBoard.length; y++) {
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
				Board board = SudokuBoardHelper.getBoard(solvableBoard, new SudokuSolverUsingBruteForceAlgorithm());
				if (board.getSudokuCells()[x][y].getValue() == 0) {
					if (!board.isValueValidToAdd(x, y, solvedBoard[x][y])) isOk = false;
				}
			}
		}
		assertTrue(!isOk);
	}

	@Test
	public void testIsBoardSolvable() throws Exception {
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
		Board board = SudokuBoardHelper.getBoard(solvableBoard, new SudokuSolverUsingBruteForceAlgorithm());
		SudokuSolverUsingBruteForceAlgorithm bruteForceSudokuSolverAlgorithm = new SudokuSolverUsingBruteForceAlgorithm();
		Board solved = bruteForceSudokuSolverAlgorithm.solveSudokuBoard(board,5);
		
		int[][] solvedBoard = { 
				{6,2,1, 4,7,8, 5,9,3},
				{4,9,7, 3,5,6, 2,1,8},
				{8,5,3, 2,9,1, 4,6,7},
				
				{7,3,6, 1,2,5, 9,8,4},
				{5,1,8, 9,4,3, 7,2,6},
				{2,4,9, 8,6,7, 1,3,5},
				
				{1,6,5, 7,8,9, 3,4,2},
				{3,7,4, 6,1,2, 8,5,9},
				{9,8,2, 5,3,4, 6,7,1},
		};
		
		boolean isOk = true;
		for (int x=0; x<solvedBoard.length; x++) {
			for (int y=0; y<solvedBoard.length; y++) {
				if (solved.getSudokuCells()[x][y].getValue() != solvedBoard[x][y]) {
					isOk = false;
				}
			}
		}
		assertTrue(isOk);
	}
	
	@Test
	public void testIsBoardSolvableFrom0() throws Exception {

		Board board = new Board(new InitializationBoardWithZerosStrategy(), new BoardValidationWithBackupStrategy(), new SudokuSolverUsingBruteForceAlgorithm());
		SudokuSolverUsingBruteForceAlgorithm bruteForceSudokuSolverAlgorithm = new SudokuSolverUsingBruteForceAlgorithm();
		Board solved = bruteForceSudokuSolverAlgorithm.solveSudokuBoard(board,5);
		SudokuBoardTextPrinter.printSudokuBoard(solved.getSudokuCells());
		assertTrue(true);
	}
	
	@Test
	public void testIfFindNextSolutionWork() throws Exception {
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
		Board board = SudokuBoardHelper.getBoard(solvableBoard, new SudokuSolverUsingBruteForceAlgorithm());
		System.out.println(board.findNextSolution());
		assertTrue(true);
	}
	

	
	


}
