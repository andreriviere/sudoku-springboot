package wt.sudoku.command;

import java.util.Arrays;

public class BoardCommand {

	private int[][] sudokuBoardCells;

	public BoardCommand() {
	}

	public BoardCommand(int[][] sudokuBoardCells) {
		this.sudokuBoardCells = sudokuBoardCells;
	}

	public int[][] getSudokuBoardCells() {
		return sudokuBoardCells;
	}

	public void setSudokuBoardCells(int[][] sudokuBoardCells) {
		this.sudokuBoardCells = sudokuBoardCells;
	}

	@Override
	public String toString() {
		return "BoardCommand [sudokuBoardCells=" + Arrays.toString(sudokuBoardCells) + "]";
	}
	

}
