package model.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.enums.ColumnType;

public class Columns {

	private Map<ColumnType, List<Integer>> columns;
	
	public Columns() {
		columns = new HashMap<ColumnType, List<Integer>>();
		columns.put(ColumnType.ColumnA, new ArrayList<Integer>());
		columns.put(ColumnType.ColumnB, new ArrayList<Integer>());
		columns.put(ColumnType.ColumnC, new ArrayList<Integer>());
		columns.put(ColumnType.ColumnD, new ArrayList<Integer>());
		columns.put(ColumnType.ColumnE, new ArrayList<Integer>());
		columns.put(ColumnType.ColumnF, new ArrayList<Integer>());
		columns.put(ColumnType.ColumnG, new ArrayList<Integer>());
		columns.put(ColumnType.ColumnH, new ArrayList<Integer>());
		columns.put(ColumnType.ColumnI, new ArrayList<Integer>());
	}
	
	public void addValueToType(ColumnType columnType, int value) {
		columns.get(columnType).add(value);
	}
	
	public void removeElementFromType(ColumnType columnType, int value) {
		columns.get(columnType).remove(Integer.valueOf(value));
	}

	public Map<ColumnType, List<Integer>> getColumns() {
		return columns;
	}
	
	
	
}
