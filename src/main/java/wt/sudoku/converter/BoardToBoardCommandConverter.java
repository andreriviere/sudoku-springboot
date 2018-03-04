package wt.sudoku.converter;

import java.util.Objects;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wt.sudoku.command.BoardCommand;
import wt.sudoku.model.BoardPlay;
import wt.sudoku.model.board.main.Cell;

@Component
public class BoardToBoardCommandConverter implements Converter<BoardPlay, BoardCommand> {

	@Override
	public BoardCommand convert(BoardPlay board) {
		Objects.nonNull(board);
		Cell[][] cells = board.getSudokuCells();
		int[][] boardCells = new int[cells.length][];
		for (int x = 0; x < cells.length; x++) {
			boardCells[x] = new int[cells[x].length];
			for (int y = 0; y < cells[x].length; y++) {
				boardCells[x][y] = cells[y][x].getValue();
			}
		}

		return new BoardCommand(boardCells);
	}

}
