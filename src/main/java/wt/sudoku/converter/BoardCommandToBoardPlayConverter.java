package wt.sudoku.converter;

import java.util.Objects;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wt.sudoku.command.BoardCommand;
import wt.sudoku.model.BoardPlay;
import wt.sudoku.model.BoardValidationWithBackupStrategy;
import wt.sudoku.model.InitializationBoardWithZerosStrategy;
import wt.sudoku.model.board.main.Board;

@Component
public class BoardCommandToBoardPlayConverter implements Converter<BoardCommand, BoardPlay> {

	@Override
	public BoardPlay convert(BoardCommand boardCommand) {
		Objects.nonNull(boardCommand);
		BoardPlay boardPlay = new Board(new InitializationBoardWithZerosStrategy(),
				new BoardValidationWithBackupStrategy());
		for (int x = 0; x < boardCommand.getSudokuBoardCells().length; x++) {
			for (int y = 0; y < boardCommand.getSudokuBoardCells()[x].length; y++) {
				boardPlay.addValueToCell(x, y, boardCommand.getSudokuBoardCells()[x][y]);
			}
		}
		return boardPlay;
	}

}
