package wt.sudoku.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wt.sudoku.command.BoardCommand;
import wt.sudoku.model.BoardPlay;
import wt.sudoku.model.BoardValidationWithBackupStrategy;
import wt.sudoku.model.InitializationBoardWithZerosStrategy;
import wt.sudoku.model.board.main.Board;
import wt.sudoku.solver.SudokuSolverAlgorithm;

@Component
public class BoardCommandToBoardPlayConverter implements Converter<BoardCommand, BoardPlay> {

	private SudokuSolverAlgorithm sudokuSolverAlgorithm;

	@Autowired
	public BoardCommandToBoardPlayConverter(@Qualifier("getSudokuSolverAlgorithm") SudokuSolverAlgorithm sudokuSolverAlgorithm) {
		this.sudokuSolverAlgorithm = sudokuSolverAlgorithm;
	}
	
	@Override
	public BoardPlay convert(BoardCommand boardCommand) {
		Objects.nonNull(boardCommand);
		
		BoardPlay boardPlay = new Board(new InitializationBoardWithZerosStrategy(),
				new BoardValidationWithBackupStrategy(), sudokuSolverAlgorithm);
		for (int y = 0; y < boardCommand.getSudokuBoardCells().length; y++) {
			for (int x = 0; x < boardCommand.getSudokuBoardCells()[y].length; x++) {
				boardPlay.addValueToCell(x, y, boardCommand.getSudokuBoardCells()[x][y]);
			}
		}
		return boardPlay;
	}

}
