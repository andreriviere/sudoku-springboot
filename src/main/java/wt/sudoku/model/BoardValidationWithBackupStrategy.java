package wt.sudoku.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import wt.sudoku.model.board.main.Board;
import wt.sudoku.model.board.main.Cell;
import wt.sudoku.model.elements.container.CellParams;
import wt.sudoku.model.elements.related.OptionalRelationCol;
import wt.sudoku.model.elements.related.OptionalRelationRow;
import wt.sudoku.model.enums.Col3PairType;
import wt.sudoku.model.enums.Row3PairType;

public class BoardValidationWithBackupStrategy implements BoardValidationStrategy {

	private Board board;

	@Override
	public boolean isBoardValidAfterAddValue(Board board, int value) {
		this.board = board;
		for (int y = 0; y < board.getSudokuCells().length; y++) {
			for (int x = 0; x < board.getSudokuCells()[y].length; x++) {
				int saveCurrentValueToBackupWhenNotAcceptable = board.getSudokuCells()[x][y].getValue();
				if (saveCurrentValueToBackupWhenNotAcceptable != 0) {
					board.getSudokuCells()[x][y].deleteValue();
					if (!isValueAcceptable(board, x, y, saveCurrentValueToBackupWhenNotAcceptable)) {
						return false;
					}
					backupValue(x, y, saveCurrentValueToBackupWhenNotAcceptable);
				}
			}
		}

		return true;
	}
	
	@Override
	public boolean isValueAcceptable(Board board, int x, int y, int value) {
		this.board = board;
		if (!board.getSudokuCells()[x][y].isValueAcceptable(value)) {
			return false;
		}
			
		CellParams cellParams = board.getSudokuCells()[x][y].getCellParams();
		Col3PairType colPairType = cellParams.getCol3PairType();
		Row3PairType rowPairType = cellParams.getRow3PairType();
		List<OptionalRelationCol> relationColList = board.getBoardMap().getCol3Pairs()
				.getOptionalRelationCol(colPairType);
		List<OptionalRelationRow> relationRowList = board.getBoardMap().getRow3Pairs()
				.getOptionalRelationRow(rowPairType);
		boolean valueCanBeSet = true;
		for (OptionalRelationCol relationCol : relationColList) {

			List<Integer> relation1Cells = getCellByCol3PairType(relationCol.getRelation1());
			List<Integer> relation2Cells = getCellByCol3PairType(relationCol.getRelation2());
			if (ifRelationsCanBeAvoidFromChecking(value, relation1Cells, relation2Cells))
				return true;

			if (ifRelation1ContainValueAndRelation2DontHave0(value, relation1Cells, relation2Cells)) {
				valueCanBeSet = false;
			}
			if (ifRelation1ContainValueAndRelation2DontHave0(value, relation2Cells, relation1Cells)
					&& ifValueCantBeSetForTwoCombinations(valueCanBeSet)) {
				valueCanBeSet = false;
			}
		}

		if (ifValueCantBeSetForTwoCombinations(valueCanBeSet))
			return false;
		valueCanBeSet = true;
		for (OptionalRelationRow relationRow : relationRowList) {

			List<Integer> relation1Cells = getCellByRow3PairType(relationRow.getRelation1());
			List<Integer> relation2Cells = getCellByRow3PairType(relationRow.getRelation2());
			if (ifRelationsCanBeAvoidFromChecking(value, relation1Cells, relation2Cells))
				return true;
			if (ifRelation1ContainValueAndRelation2DontHave0(value, relation1Cells, relation2Cells)) {
				valueCanBeSet = false;
			}
			if (ifRelation1ContainValueAndRelation2DontHave0(value, relation2Cells, relation1Cells)
					&& ifValueCantBeSetForTwoCombinations(valueCanBeSet)) {
				valueCanBeSet = false;
			}
		}

		if (ifValueCantBeSetForTwoCombinations(valueCanBeSet))
			return false;

		return true;
	}

	private boolean ifValueCantBeSetForTwoCombinations(boolean valueCanBeSet) {
		return valueCanBeSet == false;
	}

	private boolean ifRelationsCanBeAvoidFromChecking(int value, List<Integer> relation1Cells,
			List<Integer> relation2Cells) {
		return ifRelationsHaveTwoZeros(relation1Cells, relation2Cells)
				|| ifRelationsThatSame(relation1Cells, relation2Cells)
				|| relation1Cells.contains(value) && relation2Cells.contains(value);
	}

	private boolean ifRelationsThatSame(List<Integer> relation1Cells, List<Integer> relation2Cells) {
		return relation1Cells.containsAll(relation2Cells);
	}

	private List<Integer> getCellByCol3PairType(Col3PairType col3PairType) {
		List<Cell> cellToList = Arrays.stream(board.getSudokuCells()).flatMap(Arrays::stream)
				.collect(Collectors.toList());
		cellToList = cellToList.stream().filter(e -> e.getCellParams().getCol3PairType().equals(col3PairType))
				.collect(Collectors.toList());
		return cellToList.stream().map(e -> e.getValue()).collect(Collectors.toList());

	}

	private List<Integer> getCellByRow3PairType(Row3PairType row3PairType) {
		List<Cell> cellToList = Arrays.stream(board.getSudokuCells()).flatMap(Arrays::stream)
				.collect(Collectors.toList());
		cellToList = cellToList.stream().filter(e -> e.getCellParams().getRow3PairType().equals(row3PairType))
				.collect(Collectors.toList());
		return cellToList.stream().map(e -> e.getValue()).collect(Collectors.toList());
	}

	private void backupValue(int x, int y, int value) {
		throwExceptionWhenUnacceptableArgs(x, y, value);
		board.getSudokuCells()[x][y].addValue(value);
	}

	private boolean ifRelation1ContainValueAndRelation2DontHave0(int value, List<Integer> relation1Cells,
			List<Integer> relation2Cells) {
		return relation1Cells.contains(value) && !relation2Cells.contains(0);
	}

	private boolean ifRelationsHaveTwoZeros(List<Integer> relation1Cells, List<Integer> relation2Cells) {
		return relation2Cells.contains(0) && relation1Cells.contains(0);
	}

	private void throwExceptionWhenUnacceptableArgs(int x, int y, int value) {
		if ((x < 0 || x >= 9) || (y < 0 || y >= 9) || (value < 0 || value > 9))
			throw new RuntimeException("Invalid arguments");
	}

}
