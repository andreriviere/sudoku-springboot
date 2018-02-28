package wt.sudoku.model.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wt.sudoku.model.enums.RowType;

public class Rows {

	private Map<RowType, List<Integer>> rows;
	
	public Rows() {
		rows = new HashMap<RowType, List<Integer>>();
		rows.put(RowType.ROW1, new ArrayList<Integer>());
		rows.put(RowType.ROW2, new ArrayList<Integer>());
		rows.put(RowType.ROW3, new ArrayList<Integer>());
		rows.put(RowType.ROW4, new ArrayList<Integer>());
		rows.put(RowType.ROW5, new ArrayList<Integer>());
		rows.put(RowType.ROW6, new ArrayList<Integer>());
		rows.put(RowType.ROW7, new ArrayList<Integer>());
		rows.put(RowType.ROW8, new ArrayList<Integer>());
		rows.put(RowType.ROW9, new ArrayList<Integer>());
	}
	
	public void addValueToType(RowType rowType, int value) {
		rows.get(rowType).add(value);
	}
	
	public void removeElementFromType(RowType rowType, int value) {
		rows.get(rowType).remove(Integer.valueOf(value));
	}

	public Map<RowType, List<Integer>> getRows() {
		return rows;
	}

}
