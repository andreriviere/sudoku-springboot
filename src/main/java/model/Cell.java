package model;


import model.elements.container.BoardMap;
import model.elements.container.CellParams;

public class Cell implements Cloneable {

	private int value;
	private CellParams cellParams;
	private BoardMap boardMap;

	public Cell(CellParams cellParams, BoardMap boardMap) {
		this.cellParams = cellParams;
		this.boardMap = boardMap;
		value = 0;
	}

	public void addValue(int value) {
		boardMap.getRects().addValueToType(cellParams.getRectType(), value);
		boardMap.getRows().addValueToType(cellParams.getRowType(), value);
		boardMap.getColumns().addValueToType(cellParams.getColumnType(), value);
		this.value = value;
	}
	
	public void deleteValue(int value) {
		boardMap.getRects().removeElementFromType(cellParams.getRectType(), value);
		boardMap.getRows().removeElementFromType(cellParams.getRowType(), value);
		boardMap.getColumns().removeElementFromType(cellParams.getColumnType(), value);
		this.value = 0;
	}

	public boolean isValueAcceptable(int value) {
		return !boardMap.getRects().getRects().get(cellParams.getRectType()).contains(value)
				&& !boardMap.getColumns().getColumns().get(cellParams.getColumnType()).contains(value)
				&& !boardMap.getRows().getRows().get(cellParams.getRowType()).contains(value);
	}

	public int getValue() {
		return value;
	}

	public CellParams getCellParams() {
		return cellParams;
	}

	public void setCellParams(CellParams cellParams) {
		this.cellParams = cellParams;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
