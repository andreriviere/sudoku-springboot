package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.elements.container.BoardMap;
import model.elements.container.CellParams;
import model.elements.related.OptionalRelationCol;
import model.elements.related.OptionalRelationRow;
import model.enums.Col3PairType;
import model.enums.ColumnType;
import model.enums.Rect3X3Type;
import model.enums.Row3PairType;
import model.enums.RowType;

public class Board {

	private Cell[][] sudokuCells;
	private BoardMap boardMap;
	private List<Cell> addValueHistory;

	public Board() {
		this.sudokuCells = new Cell[9][9];
		addValueHistory = new ArrayList<Cell>();
		initializeBoard();
	}
	
	public void addValueToCell(int x, int y, int value) {
		if ((x < 0 || x >= sudokuCells.length) || (y < 0 || y >= sudokuCells.length) || (value < 1 || value > 9)) throw new RuntimeException("Invalid arguments");
		sudokuCells[x][y].addValue(value);
		if (!calculateAllBoardValid(sudokuCells)) {
			sudokuCells[x][y].deleteValue(value);
		}
		try {
			addValueHistory.add((Cell) sudokuCells[x][y].clone());
		} catch (CloneNotSupportedException e) {
			//avoid
		}
	}
	
	public void backupValue(int x, int y, int value) {
		if ((x < 0 || x >= sudokuCells.length) || (y < 0 || y >= sudokuCells.length) || (value < 1 || value > 9)) throw new RuntimeException("Invalid arguments");
		sudokuCells[x][y].addValue(value);
	}
	
	public int acceptablePossibilities(int x, int y) {
		int count = 0;
		for (int i=0; i<9; i++) {
			count += isValueAcceptableToCell(x,y,i) ? 1 : 0;
		}
		return count;
	}
	
	public boolean isValueAcceptableToCell(int x, int y, int value) {
		if ((x < 0 || x >= sudokuCells.length) || (y < 0 || y >= sudokuCells.length) || (value < 0 || value > 9)) throw new RuntimeException("Invalid arguments");
		if (sudokuCells[x][y].isValueAcceptable(value)) {
			CellParams cellParams = sudokuCells[x][y].getCellParams();
			Col3PairType colPairType = cellParams.getCol3PairType();
			Row3PairType rowPairType = cellParams.getRow3PairType();
			List<OptionalRelationCol> relationColList = boardMap.getCol3Pairs().getOptionalRelationCol(colPairType);
			List<OptionalRelationRow> relationRowList = boardMap.getRow3Pairs().getOptionalRelationRow(rowPairType);
			boolean valueCanBeSet = true;
			for (OptionalRelationCol relationCol : relationColList) {
				List<Integer> relation1Cells = getCellByCol3PairType(relationCol.getRelation1());
				List<Integer> relation2Cells = getCellByCol3PairType(relationCol.getRelation2());
				if (relation1Cells.contains(value) && !relation2Cells.contains(0)) return false;
				if (relation2Cells.contains(value) && !relation1Cells.contains(0)) return false;
				if (valueCanBeSet == false)
				if (!relation1Cells.contains(0) || relation2Cells.contains(0)) return false;
			}
			
			for (OptionalRelationRow relationRow : relationRowList) {
				List<Integer> relation1Cells = getCellByRow3PairType(relationRow.getRelation1());
				List<Integer> relation2Cells = getCellByRow3PairType(relationRow.getRelation2());
				if (relation1Cells.contains(value) && !relation2Cells.contains(0)) return false;
				if (relation2Cells.contains(value) && !relation1Cells.contains(0)) return false;
				if (valueCanBeSet == false)
				if (!relation1Cells.contains(0) || relation2Cells.contains(0)) return false;
			}
			
			return true;
		}
		return false;
	}

	private List<Integer> getCellByCol3PairType(Col3PairType col3PairType) {
		List<Cell> cellToList = Arrays.stream(sudokuCells)
			    .flatMap(Arrays::stream)
			    .collect(Collectors.toList());
		cellToList = cellToList.stream().filter(e -> e.getCellParams().getCol3PairType().equals(col3PairType)).collect(Collectors.toList());
		return cellToList.stream().map(e -> e.getValue()).collect(Collectors.toList());
		
	}
	
	private List<Integer> getCellByRow3PairType(Row3PairType row3PairType) {
		List<Cell> cellToList = Arrays.stream(sudokuCells)
			    .flatMap(Arrays::stream)
			    .collect(Collectors.toList());
		cellToList = cellToList.stream().filter(e -> e.getCellParams().getRow3PairType().equals(row3PairType)).collect(Collectors.toList());
		return cellToList.stream().map(e -> e.getValue()).collect(Collectors.toList());
	}
	
	private boolean calculateAllBoardValid(Cell[][] cells) {
		for (int x=0; x<cells.length; x++) {
			for (int y=0; y<cells[x].length; y++) {
				int backupValue = cells[x][y].getValue();
				if (backupValue != 0)
				{
				cells[x][y].deleteValue(cells[x][y].getValue());
				}
				if (!isValueAcceptableToCell(x, y, cells[x][y].getValue())) return false;
				if (backupValue != 0)
				{
				backupValue(x, y, backupValue);
				}
			}
		}
		return true;
	}
	
	
	private void initializeBoard() {
		boardMap = new BoardMap();
		
		for (int x=0; x<sudokuCells.length; x++)
		{
			for (int y=0; y<sudokuCells[x].length; y++) {
				CellParams cellParams = new CellParams(Rect3X3Type.calculateRectType(x, y), ColumnType.calculateColumnType(x), RowType.calculateRowType(y), 
						Col3PairType.calculateCol3Type(x, y), Row3PairType.calculateRow3Type(x, y));
				sudokuCells[x][y] = new Cell(cellParams, boardMap);
			}
		}
	}
	
	public List<Cell> getAddValueHistory() {
		return addValueHistory;
	}

	public Cell[][] getSudokuCells() {
		return sudokuCells;
	}

	public BoardMap getBoardMap() {
		return boardMap;
	}
	
	

}

	
