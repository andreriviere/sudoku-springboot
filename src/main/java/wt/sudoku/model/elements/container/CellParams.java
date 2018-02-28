package wt.sudoku.model.elements.container;

import wt.sudoku.model.enums.Col3PairType;
import wt.sudoku.model.enums.ColumnType;
import wt.sudoku.model.enums.Rect3X3Type;
import wt.sudoku.model.enums.Row3PairType;
import wt.sudoku.model.enums.RowType;

public class CellParams {

	private Rect3X3Type rectType;
	private ColumnType columnType;
	private RowType rowType;
	private Col3PairType col3PairType;
	private Row3PairType row3PairType;
	
	public CellParams(Rect3X3Type rectType, ColumnType columnType, RowType rowType, Col3PairType col3PairType, Row3PairType row3PairType) {
		this.rectType = rectType;
		this.columnType = columnType;
		this.rowType = rowType;
		this.col3PairType = col3PairType;
		this.row3PairType = row3PairType;
	}

	public Rect3X3Type getRectType() {
		return rectType;
	}

	public ColumnType getColumnType() {
		return columnType;
	}

	public RowType getRowType() {
		return rowType;
	}

	public Col3PairType getCol3PairType() {
		return col3PairType;
	}

	public Row3PairType getRow3PairType() {
		return row3PairType;
	}
	
}
