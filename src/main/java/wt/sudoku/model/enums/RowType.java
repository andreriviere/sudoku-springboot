package wt.sudoku.model.enums;

public enum RowType {
	ROW1, ROW2, ROW3, ROW4, ROW5, ROW6, ROW7, ROW8, ROW9;
	
	public static RowType calculateRowType(int y) {
		switch(y) {
		case 0:
			return ROW1;
		case 1:
			return ROW2;
		case 2:
			return ROW3;
		case 3:
			return ROW4;
		case 4:
			return ROW5;
		case 5:
			return ROW6;
		case 6:
			return ROW7;
		case 7:
			return ROW8;
		case 8:
			return ROW9;
		default: throw new RuntimeException("Invalid size of array");
	}
	}
}
