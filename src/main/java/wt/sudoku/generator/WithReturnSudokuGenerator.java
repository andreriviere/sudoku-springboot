package wt.sudoku.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import wt.sudoku.model.BoardPlay;
import wt.sudoku.model.BoardValidationWithBackupStrategy;
import wt.sudoku.model.InitializationBoardWithZerosStrategy;
import wt.sudoku.model.board.main.Board;
import wt.sudoku.solver.SudokuSolverAlgorithm;

@Service(value = "withReturnSudokuGenerator")
public class WithReturnSudokuGenerator implements SudokuBoardGenerator{

	private SudokuSolverAlgorithm sudokuSolverAlgorithm;
	
	@Autowired
	public WithReturnSudokuGenerator(@Qualifier("sudokuSolverUsingReturnAlgorithm") SudokuSolverAlgorithm sudokuSolverAlgorithm) {
		this.sudokuSolverAlgorithm = sudokuSolverAlgorithm;
	}

	@Override
	public BoardPlay generateSolvableBoard(SudokuLevel sudokuLevel) {
		Board board = new Board(new InitializationBoardWithZerosStrategy(), new BoardValidationWithBackupStrategy(), sudokuSolverAlgorithm);
		Board solvedBoard = sudokuSolverAlgorithm.solveSudokuBoard(board, 10);
		solvedBoard = sudokuLevel.adjustBoardToSudokuLevel(solvedBoard);
		return solvedBoard;
	}

}
