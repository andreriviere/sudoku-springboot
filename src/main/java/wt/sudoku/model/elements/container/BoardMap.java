package wt.sudoku.model.elements.container;

import wt.sudoku.model.elements.Columns;
import wt.sudoku.model.elements.Rect3X3s;
import wt.sudoku.model.elements.Rows;
import wt.sudoku.model.elements.related.Col3Pairs;
import wt.sudoku.model.elements.related.Row3Pairs;

public class BoardMap {

	private Rect3X3s rects;
	private Columns columns;
	private Rows rows;
	private Col3Pairs col3Pairs;
	private Row3Pairs row3Pairs;
	
	public BoardMap() {
		rects = new Rect3X3s();
		columns = new Columns();
		rows = new Rows();
		col3Pairs = new Col3Pairs();
		row3Pairs = new Row3Pairs();
	}

	public Rect3X3s getRects() {
		return rects;
	}

	public Columns getColumns() {
		return columns;
	}

	public Rows getRows() {
		return rows;
	}

	public Col3Pairs getCol3Pairs() {
		return col3Pairs;
	}

	public Row3Pairs getRow3Pairs() {
		return row3Pairs;
	}

	

}
