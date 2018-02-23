package model.enums;

public enum ColumnType {
	ColumnA, ColumnB, ColumnC, ColumnD, ColumnE, ColumnF, ColumnG, ColumnH, ColumnI;
	
	public static ColumnType calculateColumnType(int x) {
		switch(x) {
		case 0:
			return ColumnA;
		case 1:
			return ColumnB;
		case 2:
			return ColumnC;
		case 3:
			return ColumnD;
		case 4:
			return ColumnE;
		case 5:
			return ColumnF;
		case 6:
			return ColumnG;
		case 7:
			return ColumnH;
		case 8:
			return ColumnI;
		default: throw new RuntimeException("Invalid size of array");
	}
	}
}
