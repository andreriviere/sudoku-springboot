package wt.sudoku.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wt.sudoku.model.BoardPlay;
import wt.sudoku.model.BoardValidationWithBackupStrategy;
import wt.sudoku.model.InitializationBoardWithZerosStrategy;
import wt.sudoku.model.board.main.Board;
import wt.sudoku.solver.BruteForceSudokuSolverAlgorithm;
import wt.sudoku.utils.BoardStatisticsHelper;

@Service(value = "bruteForceSudokuGenerator")
public class BruteForceSudokuGenerator implements SudokuBoardGenerator{

	private BruteForceSudokuSolverAlgorithm bruteForceSudokuSolverAlgorithm;
	
	@Autowired
	public BruteForceSudokuGenerator(BruteForceSudokuSolverAlgorithm bruteForceSudokuSolverAlgorithm) {
		this.bruteForceSudokuSolverAlgorithm = bruteForceSudokuSolverAlgorithm;
	}

	@Override
	public BoardPlay generateSolvableBoard(SudokuLevel sudokuLevel) {
		Board board = new Board(new InitializationBoardWithZerosStrategy(), new BoardValidationWithBackupStrategy());
		Board solvedBoard = bruteForceSudokuSolverAlgorithm.solveSudokuBoard(board, 10);
		solvedBoard = sudokuLevel.adjustBoardToSudokuLevel(solvedBoard);
		return solvedBoard;
	}

}
