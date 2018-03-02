package wt.sudoku.model.board.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rits.cloning.Cloner;

import wt.sudoku.model.BoardPlay;
import wt.sudoku.model.BoardValidationStrategy;
import wt.sudoku.model.InitializationBoardStrategy;
import wt.sudoku.model.elements.container.BoardMap;
import wt.sudoku.solver.BruteForceSudokuSolverAlgorithm;
import wt.sudoku.utils.BoardStatisticsHelper;

public class Board implements BoardPlay {

	private static final Logger logger = LoggerFactory.getLogger(Board.class);
	private final int BOARD_X_SIZE = 9;
	private final int BOARD_Y_SIZE = 9;

	private Cell[][] sudokuCells;
	private BoardMap boardMap;
	private List<Cell> addValueHistory;

	private BoardValidationStrategy boardValidationStrategy;

	public Board(InitializationBoardStrategy initializationBoardStrategy,
			BoardValidationStrategy boardValidationStrategy) {
		this.boardValidationStrategy = boardValidationStrategy;
		this.sudokuCells = new Cell[BOARD_X_SIZE][BOARD_Y_SIZE];
		addValueHistory = new ArrayList<Cell>();
		boardMap = new BoardMap();
		initializationBoardStrategy.initializeBoard(this);
		fillListWithValidValuesPerEachCell();
	}

	@Override
	public void addValueToCell(int x, int y, int value) {
		throwExceptionWhenUnacceptableArgs(x, y, value);
		sudokuCells[x][y].addValue(value);
		addCellToCellHistory(x, y);
		
	}

	@Override
	public void deleteValue(int x, int y) {
		sudokuCells[x][y].deleteValue();
		addCellToCellHistory(x, y);
	}

	@Override
	public boolean isValueValidToAdd(int x, int y, int value) {
		fillListWithValidValuesPerEachCell();
		addValueToCell(x, y, value);
		return boardValidationStrategy.isBoardValidAfterAddValue(this, value);
	}

	public void fillListWithValidValuesPerEachCell() {
		for (int x = 0; x < sudokuCells.length; x++) {
			for (int y = 0; y < sudokuCells[x].length; y++) {
				generatePossibleValueAndCheckIfBoardIsValid(x, y);
			}
		}
	}
	
	@Override
	public Cell findNextSolution() {
		fillListWithValidValuesPerEachCell();
		if (!isBoardSolvable()) throw new RuntimeException("Unsolvable sudoku board");
		List<Cell> cellToList = Arrays.stream(sudokuCells).flatMap(Arrays::stream)
				.filter(c -> c.getValue() == 0)
				.sorted((c1, c2) -> Integer.compare(c1.getAvailableValues().size(), c2.getAvailableValues().size()))
				.collect(Collectors.toList());
		if (cellToList.isEmpty()) return null;
		Random random = new Random();
		cellToList.get(0).addValue(cellToList.get(0).getAvailableValues().get(random.nextInt(cellToList.get(0).getAvailableValues().size())));
		return cellToList.get(0);
	}
	
	@Override
	public boolean isBoardSolvable() {
		Cloner cloner = new Cloner();
		Board clonedBoard = cloner.deepClone(this);
		BruteForceSudokuSolverAlgorithm bruteForceSudokuSolverAlgorithm = new BruteForceSudokuSolverAlgorithm();
		clonedBoard = bruteForceSudokuSolverAlgorithm.solveSudokuBoard(clonedBoard, 100);
		System.out.println(BoardStatisticsHelper.calculateFilledFields(clonedBoard));
		return BoardStatisticsHelper.calculateFilledFields(clonedBoard) == 81;
	}

	@Override
	public void cleanHistory() {
		addValueHistory.clear();
	}

	@Override
	public Cell[][] getSudokuCells() {
		return sudokuCells;
	}

	public List<Cell> getAddValueHistory() {
		return addValueHistory;
	}

	public BoardMap getBoardMap() {
		return boardMap;
	}

	private void addCellToCellHistory(int x, int y) {
		try {
			addValueHistory.add((Cell) sudokuCells[x][y].clone());
		} catch (CloneNotSupportedException e) {
			logger.error("Cell history clone error!", e);
		}
	}

	private void generatePossibleValueAndCheckIfBoardIsValid(int x, int y) {
		List<Integer> acceptableValues = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++) {
			if (boardValidationStrategy.isValueAcceptable(this, x, y, i))
				acceptableValues.add(i);
		}

		sudokuCells[x][y].setAvailableValues(acceptableValues);
	}

	private void throwExceptionWhenUnacceptableArgs(int x, int y, int value) {
		if ((x < 0 || x >= 9) || (y < 0 || y >= 9) || (value < 0 || value > 9))
			throw new RuntimeException("Invalid arguments");
	}

	

}
