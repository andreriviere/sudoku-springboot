package wt.sudoku.model.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wt.sudoku.model.enums.Rect3X3Type;

public class Rect3X3s {

	private Map<Rect3X3Type, List<Integer>> rects;
	
	public Rect3X3s() {
		rects = new HashMap<Rect3X3Type, List<Integer>>();
		rects.put(Rect3X3Type.TOP_LEFT, new ArrayList<Integer>());
		rects.put(Rect3X3Type.TOP_MIDDLE, new ArrayList<Integer>());
		rects.put(Rect3X3Type.TOP_RIGHT, new ArrayList<Integer>());
		rects.put(Rect3X3Type.MIDDLE_LEFT, new ArrayList<Integer>());
		rects.put(Rect3X3Type.CENTER, new ArrayList<Integer>());
		rects.put(Rect3X3Type.MIDDLE_RIGHT, new ArrayList<Integer>());
		rects.put(Rect3X3Type.BOTTOM_LEFT, new ArrayList<Integer>());
		rects.put(Rect3X3Type.BOTTOM_MIDDLE, new ArrayList<Integer>());
		rects.put(Rect3X3Type.BOTTOM_RIGHT, new ArrayList<Integer>());
	}
	
	public void addValueToType(Rect3X3Type rect3X3Type, int value) {
		rects.get(rect3X3Type).add(value);
	}
	
	public void removeElementFromType(Rect3X3Type rect3X3Type, int value) {
		rects.get(rect3X3Type).remove(Integer.valueOf(value));
	}

	public Map<Rect3X3Type, List<Integer>> getRects() {
		return rects;
	}
	
	
}
