package wt.sudoku.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import wt.sudoku.model.BoardPlay;
import wt.sudoku.model.BoardValidationWithBackupStrategy;
import wt.sudoku.model.InitializationBoardWithZerosStrategy;
import wt.sudoku.model.board.main.Board;
import wt.sudoku.model.board.main.Cell;

@Service(value = "groupFillingSudokuGenerator")
public class GroupFillingSudokuGenerator implements SudokuBoardGenerator {

	private Random randomGenerator;

	public GroupFillingSudokuGenerator() {
		randomGenerator = new Random();
	}

	@Override
	public BoardPlay generateSolvableBoard(SudokuLevel sudokuLevel) {
		Board board = new Board(new InitializationBoardWithZerosStrategy(), new BoardValidationWithBackupStrategy());
		Cell[][] sudokuCells = board.getSudokuCells();
		List<Integer> row1List = new ArrayList<Integer>();
		List<Integer> row2List = new ArrayList<Integer>();
		List<Integer> row3List = new ArrayList<Integer>();
		try {
			generateFirst3Col(board, sudokuCells, row1List, row2List, row3List);
			cleanTempRowList(row1List, row2List, row3List);
			generateSecond3Col(board, sudokuCells, row1List, row2List, row3List);
			cleanTempRowList(row1List, row2List, row3List);
			generateThird3Col(board, sudokuCells, row1List, row2List, row3List);
			sudokuLevel.adjustBoardToSudokuLevel(board);
			return board;
		} catch (Exception e) {
			return generateSolvableBoard(sudokuLevel);
		}

	}


	private void cleanTempRowList(List<Integer> row1List, List<Integer> row2List, List<Integer> row3List) {
		row1List.clear();
		row2List.clear();
		row3List.clear();
	}

	private void generateThird3Col(Board board, Cell[][] sudokuCells, List<Integer> row1List, List<Integer> row2List,
			List<Integer> row3List) throws Exception {
		for (int y = 0; y < sudokuCells.length; y++) {
			for (int x = 0; x < sudokuCells[y].length; x++) {
				if (y > 5) {
					if (y == 6 && x < 3)
						row1List.add(addCellValue(board, x, y));
					if (y == 7 && x < 3)
						row2List.add(addCellValue(board, x, y));
					if (y == 8 && x < 3)
						row3List.add(addCellValue(board, x, y));
				} else {
					continue;
				}
			}
		}

		for (int y = 0; y < sudokuCells.length; y++) {
			for (int x = 0; x < sudokuCells[y].length; x++) {
				if (y > 5) {
					if (y == 6 && x > 2 && x < 6)
						addCellValue(board, x, y, row2List.get(x % 3));
					if (y == 7 && x > 2 && x < 6)
						addCellValue(board, x, y, row3List.get(x % 3));
					if (y == 8 && x > 2 && x < 6)
						addCellValue(board, x, y, row1List.get(x % 3));
				} else {
					continue;
				}
			}
		}
		for (int y = 0; y < sudokuCells.length; y++) {
			for (int x = 0; x < sudokuCells[y].length; x++) {
				if (y > 5) {
					if (y == 6 && x > 5)
						addCellValue(board, x, y, row3List.get(x % 3));
					if (y == 7 && x > 5)
						addCellValue(board, x, y, row1List.get(x % 3));
					if (y == 8 && x > 5)
						addCellValue(board, x, y, row2List.get(x % 3));
				} else {
					continue;
				}
			}
		}
	}

	private void generateSecond3Col(Board board, Cell[][] sudokuCells, List<Integer> row1List, List<Integer> row2List,
			List<Integer> row3List) throws Exception {
		for (int y = 0; y < sudokuCells.length; y++) {
			for (int x = 0; x < sudokuCells[y].length; x++) {
				if (y > 2 && y < 6) {
					if (y == 3 && x < 3)
						row1List.add(addCellValue(board, x, y));
					if (y == 4 && x < 3)
						row2List.add(addCellValue(board, x, y));
					if (y == 5 && x < 3)
						row3List.add(addCellValue(board, x, y));
				} else
					continue;
			}
		}

		for (int y = 0; y < sudokuCells.length; y++) {
			for (int x = 0; x < sudokuCells[y].length; x++) {
				if (y > 2 && y < 6) {
					if (y == 3 && x > 2 && x < 6)
						addCellValue(board, x, y, row2List.get(x % 3));
					if (y == 4 && x > 2 && x < 6)
						addCellValue(board, x, y, row3List.get(x % 3));
					if (y == 5 && x > 2 && x < 6)
						addCellValue(board, x, y, row1List.get(x % 3));
				} else
					continue;

			}
		}

		for (int y = 0; y < sudokuCells.length; y++) {
			for (int x = 0; x < sudokuCells[y].length; x++) {
				if (y > 2 && y < 6) {
					if (y == 3 && x > 5)
						addCellValue(board, x, y, row3List.get(x % 3));
					if (y == 4 && x > 5)
						addCellValue(board, x, y, row1List.get(x % 3));
					if (y == 5 && x > 5)
						addCellValue(board, x, y, row2List.get(x % 3));
				} else
					continue;

			}
		}

	}

	private void generateFirst3Col(Board board, Cell[][] sudokuCells, List<Integer> row1List, List<Integer> row2List,
			List<Integer> row3List) throws Exception {
		for (int x = 0; x < sudokuCells.length; x++) {
			for (int y = 0; y < sudokuCells[x].length; y++) {
				if (y < 3) {
					if (y == 0 && x < 3)
						row1List.add(addCellValue(board, x, y));
					if (y == 1 && x < 3)
						row2List.add(addCellValue(board, x, y));
					if (y == 2 && x < 3)
						row3List.add(addCellValue(board, x, y));

					if (y == 0 && x > 2 && x < 6)
						addCellValue(board, x, y, row2List.get(x % 3));
					if (y == 1 && x > 2 && x < 6)
						addCellValue(board, x, y, row3List.get(x % 3));
					if (y == 2 && x > 2 && x < 6)
						addCellValue(board, x, y, row1List.get(x % 3));

					if (y == 0 && x > 5)
						addCellValue(board, x, y, row3List.get(x % 3));
					if (y == 1 && x > 5)
						addCellValue(board, x, y, row1List.get(x % 3));
					if (y == 2 && x > 5)
						addCellValue(board, x, y, row2List.get(x % 3));
				} else {
					continue;
				}
			}
		}
	}

	private int addCellValue(Board board, int x, int y) throws Exception {
		int value = board.getSudokuCells()[x][y].getAvailableValues()
				.get(randomGenerator.nextInt(board.getSudokuCells()[x][y].getAvailableValues().size()));
		board.addValueToCell(x, y, value);
		if (!board.isValueValidToAdd(x, y, value)) {
			board.deleteValue(x, y);
			board.fillListWithValidValuesPerEachCell();
			return 0;
		}

		board.fillListWithValidValuesPerEachCell();
		return value;
	}

	private int addCellValue(Board board, int x, int y, int value) {
		board.addValueToCell(x, y, value);
		if (!board.isValueValidToAdd(x, y, value)) {
			board.deleteValue(x, y);
			board.fillListWithValidValuesPerEachCell();
			return 0;
		}
		board.fillListWithValidValuesPerEachCell();
		return value;
	}

}
