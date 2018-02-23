package model.enums;

public enum Rect3X3Type {
	TOP_LEFT, TOP_MIDDLE, TOP_RIGHT,
	MIDDLE_LEFT, CENTER, MIDDLE_RIGHT,
	BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT;
	
	public static Rect3X3Type calculateRectType(int x, int y) {
		if (y >= 0 && y<3) {
			if (x >= 0 && x<3) {
				return Rect3X3Type.TOP_LEFT;
			}
			else if (x >= 3 && x<6) {
				return Rect3X3Type.TOP_MIDDLE;
			}
			else {
				return Rect3X3Type.TOP_RIGHT;
			}
		}
		else if (y >= 3 && y<6) {
			if (x >= 0 && x<3) {
				return Rect3X3Type.MIDDLE_LEFT;
			}
			else if (x >= 3 && x<6) {
				return Rect3X3Type.CENTER;
			}
			else {
				return Rect3X3Type.MIDDLE_RIGHT;
			}
		}
		else {
			if (x >= 0 && x<3) {
				return Rect3X3Type.BOTTOM_LEFT;
			}
			else if (x >= 3 && x<6) {
				return Rect3X3Type.BOTTOM_MIDDLE;
			}
			else {
				return Rect3X3Type.BOTTOM_RIGHT;
			}
		}
	}
	
}
