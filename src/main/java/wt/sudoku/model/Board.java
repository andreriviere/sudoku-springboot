package wt.sudoku.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wt.sudoku.model.elements.container.BoardMap;
import wt.sudoku.model.elements.container.CellParams;
import wt.sudoku.model.elements.related.OptionalRelationCol;
import wt.sudoku.model.elements.related.OptionalRelationRow;
import wt.sudoku.model.enums.Col3PairType;
import wt.sudoku.model.enums.ColumnType;
import wt.sudoku.model.enums.Rect3X3Type;
import wt.sudoku.model.enums.Row3PairType;
import wt.sudoku.model.enums.RowType;

public class Board implements BoardPlay {

	private static final Logger logger = LoggerFactory.getLogger(Board.class);
	private final int BOARD_X_SIZE = 9;
	private final int BOARD_Y_SIZE = 9;

	private Cell[][] sudokuCells;
	private BoardMap boardMap;
	private List<Cell> addValueHistory;

	public Board() {
		this.sudokuCells = new Cell[BOARD_X_SIZE][BOARD_Y_SIZE];
		addValueHistory = new ArrayList<Cell>();
		initializeBoard();
		fillListWithValidValuesPerEachCell();
	}

	@Override
	public void addValueToCell(int x, int y, int value) {
		throwExceptionWhenUnacceptableArgs(x, y, value);
		sudokuCells[x][y].addValue(value);
	}

	@Override
	public void deleteValue(int x, int y, int value) {
		sudokuCells[x][y].deleteValue(value);
	}

	public void addCellToCellHistory(int x, int y, int value) {
		try {
			addValueHistory.add((Cell) sudokuCells[x][y].clone());
		} catch (CloneNotSupportedException e) {
			logger.error("Cell history clone error!", e);

		}
	}

	@Override
	public boolean isValueValidToAdd(int x, int y, int value) {
		addValueToCell(x, y, value);
		boolean isValidToAdd = calculateIfAfterAddedNewValueCurrentBoardIsValid();
		deleteValue(x, y, value);
		fillListWithValidValuesPerEachCell();
		return isValidToAdd;
	}

	@Override
	public boolean isBoardSolvable() {
		for (int x = 0; x < sudokuCells.length; x++) {
			for (int y = 0; y < sudokuCells[x].length; y++) {
				if (sudokuCells[x][y].getAvailableValues().isEmpty())
					return false;
			}
		}
		
		
		return true;
	}

	public void fillListWithValidValuesPerEachCell() {
		for (int x = 0; x < sudokuCells.length; x++) {
			for (int y = 0; y < sudokuCells[x].length; y++) {
				generatePossibleValueAndCheckIfBoardIsValid(x, y);
			}
		}
	}

	public boolean isValueAcceptableToCell(int x, int y, int value) {
		throwExceptionWhenUnacceptableArgs(x, y, value);
		if (!sudokuCells[x][y].isValueAcceptable(value))
			return false;
		CellParams cellParams = sudokuCells[x][y].getCellParams();
		Col3PairType colPairType = cellParams.getCol3PairType();
		Row3PairType rowPairType = cellParams.getRow3PairType();
		List<OptionalRelationCol> relationColList = boardMap.getCol3Pairs().getOptionalRelationCol(colPairType);
		List<OptionalRelationRow> relationRowList = boardMap.getRow3Pairs().getOptionalRelationRow(rowPairType);
		boolean valueCanBeSet = true;
		for (OptionalRelationCol relationCol : relationColList) {

			List<Integer> relation1Cells = getCellByCol3PairType(relationCol.getRelation1());
			List<Integer> relation2Cells = getCellByCol3PairType(relationCol.getRelation2());
			if (ifRelationsHaveTwoZeros(relation1Cells, relation2Cells))
				return true;

			if (relation1Cells.containsAll(relation2Cells))
				return true;
			if (relation1Cells.contains(value) && relation2Cells.contains(value))
				return true;
			if (ifRelation1ContainValueAndRelation2DontHave0(value, relation1Cells, relation2Cells)) {
				valueCanBeSet = false;
			}
			if (ifRelation1ContainValueAndRelation2DontHave0(value, relation2Cells, relation1Cells)
					&& valueCanBeSet == false) {
				valueCanBeSet = false;
			}
		}

		if (valueCanBeSet == false) return false;
		valueCanBeSet = true;
		for (OptionalRelationRow relationRow : relationRowList) {

			List<Integer> relation1Cells = getCellByRow3PairType(relationRow.getRelation1());
			List<Integer> relation2Cells = getCellByRow3PairType(relationRow.getRelation2());
			if (ifRelation1ContainValueAndRelation2DontHave0(value, relation1Cells, relation2Cells)) {
				valueCanBeSet = false;
			}
			if (relation1Cells.containsAll(relation2Cells))
				return true;
			if (relation1Cells.contains(value) && relation2Cells.contains(value))
				return true;
			if (ifRelation1ContainValueAndRelation2DontHave0(value, relation2Cells, relation1Cells)
					&& valueCanBeSet == false) {
				valueCanBeSet = false;
			}

			if (ifRelationsHaveTwoZeros(relation1Cells, relation2Cells))
				return true;
		}

		if (valueCanBeSet == false)
			return false;
		return true;
	}

	public List<Cell> getAddValueHistory() {
		return addValueHistory;
	}

	@Override
	public Cell[][] getSudokuCells() {
		return sudokuCells;
	}

	public BoardMap getBoardMap() {
		return boardMap;
	}

	public boolean calculateIfAfterAddedNewValueCurrentBoardIsValid() {
		for (int y = 0; y < sudokuCells.length; y++) {
			for (int x = 0; x < sudokuCells[y].length; x++) {
				int saveCurrentValueToBackupWhenNotAcceptable = sudokuCells[x][y].getValue();
				if (saveCurrentValueToBackupWhenNotAcceptable != 0) {
					sudokuCells[x][y].deleteValue(saveCurrentValueToBackupWhenNotAcceptable);
					if (!isValueAcceptableToCell(x, y, saveCurrentValueToBackupWhenNotAcceptable)) {
						return false;
					}
					backupValue(x, y, saveCurrentValueToBackupWhenNotAcceptable);
				}
			}
		}
		return true;
	}

	private void generatePossibleValueAndCheckIfBoardIsValid(int x, int y) {
		List<Integer> acceptableValues = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++) {
			if (isValueAcceptableToCell(x, y, i))
				acceptableValues.add(i);
		}

		sudokuCells[x][y].setAvailableValues(acceptableValues);
	}

	private void initializeBoard() {
		boardMap = new BoardMap();

		for (int x = 0; x < sudokuCells.length; x++) {
			for (int y = 0; y < sudokuCells[x].length; y++) {
				CellParams cellParams = new CellParams(Rect3X3Type.calculateRectType(x, y),
						ColumnType.calculateColumnType(x), RowType.calculateRowType(y),
						Col3PairType.calculateCol3Type(x, y), Row3PairType.calculateRow3Type(x, y));
				sudokuCells[x][y] = new Cell(cellParams, boardMap);
			}
		}
	}

	private void throwExceptionWhenUnacceptableArgs(int x, int y, int value) {
		if ((x < 0 || x >= sudokuCells.length) || (y < 0 || y >= sudokuCells.length) || (value < 0 || value > 9))
			throw new RuntimeException("Invalid arguments");
	}

	private List<Integer> getCellByCol3PairType(Col3PairType col3PairType) {
		List<Cell> cellToList = Arrays.stream(sudokuCells).flatMap(Arrays::stream).collect(Collectors.toList());
		cellToList = cellToList.stream().filter(e -> e.getCellParams().getCol3PairType().equals(col3PairType))
				.collect(Collectors.toList());
		return cellToList.stream().map(e -> e.getValue()).collect(Collectors.toList());

	}

	private List<Integer> getCellByRow3PairType(Row3PairType row3PairType) {
		List<Cell> cellToList = Arrays.stream(sudokuCells).flatMap(Arrays::stream).collect(Collectors.toList());
		cellToList = cellToList.stream().filter(e -> e.getCellParams().getRow3PairType().equals(row3PairType))
				.collect(Collectors.toList());
		return cellToList.stream().map(e -> e.getValue()).collect(Collectors.toList());
	}

	private void backupValue(int x, int y, int value) {
		throwExceptionWhenUnacceptableArgs(x, y, value);
		sudokuCells[x][y].addValue(value);
	}

	private boolean ifRelation1ContainValueAndRelation2DontHave0(int value, List<Integer> relation1Cells,
			List<Integer> relation2Cells) {
		return relation1Cells.contains(value) && !relation2Cells.contains(0);
	}

	private boolean ifRelationsHaveTwoZeros(List<Integer> relation1Cells, List<Integer> relation2Cells) {
		return relation2Cells.contains(0) && relation1Cells.contains(0);
	}

}
