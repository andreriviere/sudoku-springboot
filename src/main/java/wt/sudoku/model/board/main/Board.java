package wt.sudoku.model.board.main;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rits.cloning.Cloner;
import wt.sudoku.model.BoardPlay;
import wt.sudoku.model.BoardValidationStrategy;
import wt.sudoku.model.InitializationBoardStrategy;
import wt.sudoku.model.elements.container.BoardMap;
import wt.sudoku.solver.SudokuSolverAlgorithm;
import wt.sudoku.utils.BoardStatisticsHelper;

public class Board implements BoardPlay {

	private static final Logger logger = LoggerFactory.getLogger(Board.class);
	private final int BOARD_X_SIZE = 9;
	private final int BOARD_Y_SIZE = 9;

	private Cell[][] sudokuCells;
	private BoardMap boardMap;
	private List<Cell> addValueHistory;

	private BoardValidationStrategy boardValidationStrategy;
	private SudokuSolverAlgorithm sudokuSolverAlgorithm;

	public Board(InitializationBoardStrategy initializationBoardStrategy,
			BoardValidationStrategy boardValidationStrategy, SudokuSolverAlgorithm sudokuSolverAlgorithm) {
		this.boardValidationStrategy = boardValidationStrategy;
		this.sudokuCells = new Cell[BOARD_X_SIZE][BOARD_Y_SIZE];
		this.sudokuSolverAlgorithm = sudokuSolverAlgorithm;
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
		return sudokuSolverAlgorithm.findNextSolution(this);
	}
	
	@Override
	public boolean isBoardSolvable() {
		Board clonedBoard = cloneBoard();
		clonedBoard = sudokuSolverAlgorithm.solveSudokuBoard(clonedBoard, 100);
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
	
	public Board cloneBoard() {
		Cloner cloner = new Cloner();
		return cloner.deepClone(this);
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
