package wt.sudoku.model.enums;

public enum Col3PairType {
	TOP_1, TOP_2, TOP_3, TOP_4, TOP_5, TOP_6, TOP_7, TOP_8, TOP_9,
	MIDDLE_1, MIDDLE_2, MIDDLE_3, MIDDLE_4, MIDDLE_5, MIDDLE_6, MIDDLE_7, MIDDLE_8, MIDDLE_9,
	BOTTOM_1, BOTTOM_2, BOTTOM_3, BOTTOM_4, BOTTOM_5, BOTTOM_6, BOTTOM_7, BOTTOM_8, BOTTOM_9;
	
	public static Col3PairType calculateCol3Type(int x, int y) {
		if (y >= 0 && y<3) {
			switch(x) {
			case 0:
				return TOP_1;
			case 1:
				return TOP_2;
			case 2:
				return TOP_3;
			case 3:
				return TOP_4;
			case 4:
				return TOP_5;
			case 5:
				return TOP_6;
			case 6:
				return TOP_7;
			case 7:
				return TOP_8;
			case 8:
				return TOP_9;
			default: throw new RuntimeException("Invalid size of array");
		}
		}
		else if (y >= 3 && y<6) {
			switch(x) {
			case 0:
				return MIDDLE_1;
			case 1:
				return MIDDLE_2;
			case 2:
				return MIDDLE_3;
			case 3:
				return MIDDLE_4;
			case 4:
				return MIDDLE_5;
			case 5:
				return MIDDLE_6;
			case 6:
				return MIDDLE_7;
			case 7:
				return MIDDLE_8;
			case 8:
				return MIDDLE_9;
			default: throw new RuntimeException("Invalid size of array");
		}
		}
		else {
			switch(x) {
			case 0:
				return BOTTOM_1;
			case 1:
				return BOTTOM_2;
			case 2:
				return BOTTOM_3;
			case 3:
				return BOTTOM_4;
			case 4:
				return BOTTOM_5;
			case 5:
				return BOTTOM_6;
			case 6:
				return BOTTOM_7;
			case 7:
				return BOTTOM_8;
			case 8:
				return BOTTOM_9;
			default: throw new RuntimeException("Invalid size of array");
		}
		}
	}
}
