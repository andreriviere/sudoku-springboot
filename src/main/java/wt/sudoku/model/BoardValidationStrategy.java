package wt.sudoku.model;

import wt.sudoku.model.board.main.Board;

public interface BoardValidationStrategy {

	public boolean isBoardValidAfterAddValue(Board board, int value);
	public boolean isValueAcceptable(Board board, int x, int y, int value);
}
