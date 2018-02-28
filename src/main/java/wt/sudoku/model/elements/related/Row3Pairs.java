package wt.sudoku.model.elements.related;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wt.sudoku.model.enums.Col3PairType;
import wt.sudoku.model.enums.Row3PairType;

public class Row3Pairs {

public Map<Row3PairType, List<OptionalRelationRow>> rowPairsRelation;
	
	public Row3Pairs() {
		rowPairsRelation = new HashMap<Row3PairType, List<OptionalRelationRow>>();
		generateTopColRelations();
		generateMiddleColRelations();
		generateBottomColRelations();
	}

	@SuppressWarnings("serial")
	private void generateTopColRelations() {
		
		rowPairsRelation.put(Row3PairType.TOP_1, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_2, Row3PairType.BOTTOM_3)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_3, Row3PairType.BOTTOM_2));
		}});
		
		rowPairsRelation.put(Row3PairType.TOP_2, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_1, Row3PairType.BOTTOM_3)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_3, Row3PairType.BOTTOM_1));
		}});
		
		rowPairsRelation.put(Row3PairType.TOP_3, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_1, Row3PairType.BOTTOM_2)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_2, Row3PairType.BOTTOM_1));
		}});
		
		rowPairsRelation.put(Row3PairType.TOP_4, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_5, Row3PairType.BOTTOM_6)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_6, Row3PairType.BOTTOM_5));
		}});
		
		rowPairsRelation.put(Row3PairType.TOP_5, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_4, Row3PairType.BOTTOM_6)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_6, Row3PairType.BOTTOM_4));
		}});
		
		rowPairsRelation.put(Row3PairType.TOP_6, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_4, Row3PairType.BOTTOM_5)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_5, Row3PairType.BOTTOM_4));
		}});
		
		rowPairsRelation.put(Row3PairType.TOP_7, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_8, Row3PairType.BOTTOM_9)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_9, Row3PairType.BOTTOM_8));
		}});
		
		rowPairsRelation.put(Row3PairType.TOP_8, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_7, Row3PairType.BOTTOM_9)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_9, Row3PairType.BOTTOM_7));
		}});
		
		rowPairsRelation.put(Row3PairType.TOP_9, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_7, Row3PairType.BOTTOM_8)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_8, Row3PairType.BOTTOM_7));
		}});
	}
	
	@SuppressWarnings("serial")
	private void generateMiddleColRelations() {
		
		rowPairsRelation.put(Row3PairType.MIDDLE_1, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.TOP_2, Row3PairType.BOTTOM_3)); 
			add(new OptionalRelationRow(Row3PairType.TOP_3, Row3PairType.BOTTOM_2));
		}});
		
		rowPairsRelation.put(Row3PairType.MIDDLE_2, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.TOP_1, Row3PairType.BOTTOM_3)); 
			add(new OptionalRelationRow(Row3PairType.TOP_3, Row3PairType.BOTTOM_1));
		}});
		
		rowPairsRelation.put(Row3PairType.MIDDLE_3, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.TOP_1, Row3PairType.BOTTOM_2)); 
			add(new OptionalRelationRow(Row3PairType.TOP_2, Row3PairType.BOTTOM_1));
		}});
		
		rowPairsRelation.put(Row3PairType.MIDDLE_4, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.TOP_5, Row3PairType.BOTTOM_6)); 
			add(new OptionalRelationRow(Row3PairType.TOP_6, Row3PairType.BOTTOM_5));
		}});
		
		rowPairsRelation.put(Row3PairType.MIDDLE_5, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.TOP_4, Row3PairType.BOTTOM_6)); 
			add(new OptionalRelationRow(Row3PairType.TOP_6, Row3PairType.BOTTOM_4));
		}});
		
		rowPairsRelation.put(Row3PairType.MIDDLE_6, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.TOP_4, Row3PairType.BOTTOM_5)); 
			add(new OptionalRelationRow(Row3PairType.TOP_5, Row3PairType.BOTTOM_4));
		}});
		
		rowPairsRelation.put(Row3PairType.MIDDLE_7, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.TOP_8, Row3PairType.BOTTOM_9)); 
			add(new OptionalRelationRow(Row3PairType.TOP_9, Row3PairType.BOTTOM_8));
		}});
		
		rowPairsRelation.put(Row3PairType.MIDDLE_8, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.TOP_7, Row3PairType.BOTTOM_9)); 
			add(new OptionalRelationRow(Row3PairType.TOP_9, Row3PairType.BOTTOM_7));
		}});
		
		rowPairsRelation.put(Row3PairType.MIDDLE_9, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.TOP_7, Row3PairType.BOTTOM_8)); 
			add(new OptionalRelationRow(Row3PairType.TOP_8, Row3PairType.BOTTOM_7));
		}});
	}
	
	@SuppressWarnings("serial")
	private void generateBottomColRelations() {
		
		rowPairsRelation.put(Row3PairType.BOTTOM_1, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_2, Row3PairType.TOP_3)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_3, Row3PairType.TOP_2));
		}});
		
		rowPairsRelation.put(Row3PairType.BOTTOM_2, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_1, Row3PairType.TOP_3)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_3, Row3PairType.TOP_1));
		}});
		
		rowPairsRelation.put(Row3PairType.BOTTOM_3, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_1, Row3PairType.TOP_2)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_2, Row3PairType.TOP_1));
		}});
		
		rowPairsRelation.put(Row3PairType.BOTTOM_4, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_5, Row3PairType.TOP_6)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_6, Row3PairType.TOP_5));
		}});
		
		rowPairsRelation.put(Row3PairType.BOTTOM_5, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_4, Row3PairType.TOP_6)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_6, Row3PairType.TOP_4));
		}});
		
		rowPairsRelation.put(Row3PairType.BOTTOM_6, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_4, Row3PairType.TOP_5)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_5, Row3PairType.TOP_4));
		}});
		
		rowPairsRelation.put(Row3PairType.BOTTOM_7, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_8, Row3PairType.TOP_9)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_9, Row3PairType.TOP_8));
		}});
		
		rowPairsRelation.put(Row3PairType.BOTTOM_8, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_7, Row3PairType.TOP_9)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_9, Row3PairType.TOP_7));
		}});
		
		rowPairsRelation.put(Row3PairType.BOTTOM_9, new ArrayList<OptionalRelationRow>() {{
			add(new OptionalRelationRow(Row3PairType.MIDDLE_7, Row3PairType.TOP_8)); 
			add(new OptionalRelationRow(Row3PairType.MIDDLE_8, Row3PairType.TOP_7));
		}});
	}
	
	public List<OptionalRelationRow> getOptionalRelationRow(Row3PairType row3PairType) {
		return rowPairsRelation.get(row3PairType);
	}
}
