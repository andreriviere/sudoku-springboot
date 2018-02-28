package wt.sudoku.model.elements.related;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wt.sudoku.model.enums.Col3PairType;



public class Col3Pairs {

	private Map<Col3PairType, List<OptionalRelationCol>> colPairsRelation;
	
	public Col3Pairs() {
		colPairsRelation = new HashMap<Col3PairType, List<OptionalRelationCol>>();
		generateTopColRelations();
		generateMiddleColRelations();
		generateBottomColRelations();
	}

	@SuppressWarnings("serial")
	private void generateTopColRelations() {
		
		colPairsRelation.put(Col3PairType.TOP_1, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_2, Col3PairType.BOTTOM_3)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_3, Col3PairType.BOTTOM_2));
		}});
		
		colPairsRelation.put(Col3PairType.TOP_2, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_1, Col3PairType.BOTTOM_3)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_3, Col3PairType.BOTTOM_1));
		}});
		
		colPairsRelation.put(Col3PairType.TOP_3, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_1, Col3PairType.BOTTOM_2)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_2, Col3PairType.BOTTOM_1));
		}});
		
		colPairsRelation.put(Col3PairType.TOP_4, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_5, Col3PairType.BOTTOM_6)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_6, Col3PairType.BOTTOM_5));
		}});
		
		colPairsRelation.put(Col3PairType.TOP_5, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_4, Col3PairType.BOTTOM_6)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_6, Col3PairType.BOTTOM_4));
		}});
		
		colPairsRelation.put(Col3PairType.TOP_6, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_4, Col3PairType.BOTTOM_5)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_5, Col3PairType.BOTTOM_4));
		}});
		
		colPairsRelation.put(Col3PairType.TOP_7, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_8, Col3PairType.BOTTOM_9)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_9, Col3PairType.BOTTOM_8));
		}});
		
		colPairsRelation.put(Col3PairType.TOP_8, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_7, Col3PairType.BOTTOM_9)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_9, Col3PairType.BOTTOM_7));
		}});
		
		colPairsRelation.put(Col3PairType.TOP_9, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_7, Col3PairType.BOTTOM_8)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_8, Col3PairType.BOTTOM_7));
		}});
	}
	
	@SuppressWarnings("serial")
	private void generateMiddleColRelations() {
		
		colPairsRelation.put(Col3PairType.MIDDLE_1, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.TOP_2, Col3PairType.BOTTOM_3)); 
			add(new OptionalRelationCol(Col3PairType.TOP_3, Col3PairType.BOTTOM_2));
		}});
		
		colPairsRelation.put(Col3PairType.MIDDLE_2, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.TOP_1, Col3PairType.BOTTOM_3)); 
			add(new OptionalRelationCol(Col3PairType.TOP_3, Col3PairType.BOTTOM_1));
		}});
		
		colPairsRelation.put(Col3PairType.MIDDLE_3, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.TOP_1, Col3PairType.BOTTOM_2)); 
			add(new OptionalRelationCol(Col3PairType.TOP_2, Col3PairType.BOTTOM_1));
		}});
		
		colPairsRelation.put(Col3PairType.MIDDLE_4, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.TOP_5, Col3PairType.BOTTOM_6)); 
			add(new OptionalRelationCol(Col3PairType.TOP_6, Col3PairType.BOTTOM_5));
		}});
		
		colPairsRelation.put(Col3PairType.MIDDLE_5, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.TOP_4, Col3PairType.BOTTOM_6)); 
			add(new OptionalRelationCol(Col3PairType.TOP_6, Col3PairType.BOTTOM_4));
		}});
		
		colPairsRelation.put(Col3PairType.MIDDLE_6, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.TOP_4, Col3PairType.BOTTOM_5)); 
			add(new OptionalRelationCol(Col3PairType.TOP_5, Col3PairType.BOTTOM_4));
		}});
		
		colPairsRelation.put(Col3PairType.MIDDLE_7, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.TOP_8, Col3PairType.BOTTOM_9)); 
			add(new OptionalRelationCol(Col3PairType.TOP_9, Col3PairType.BOTTOM_8));
		}});
		
		colPairsRelation.put(Col3PairType.MIDDLE_8, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.TOP_7, Col3PairType.BOTTOM_9)); 
			add(new OptionalRelationCol(Col3PairType.TOP_9, Col3PairType.BOTTOM_7));
		}});
		
		colPairsRelation.put(Col3PairType.MIDDLE_9, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.TOP_7, Col3PairType.BOTTOM_8)); 
			add(new OptionalRelationCol(Col3PairType.TOP_8, Col3PairType.BOTTOM_7));
		}});
	}
	
	@SuppressWarnings("serial")
	private void generateBottomColRelations() {
		
		colPairsRelation.put(Col3PairType.BOTTOM_1, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_2, Col3PairType.TOP_3)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_3, Col3PairType.TOP_2));
		}});
		
		colPairsRelation.put(Col3PairType.BOTTOM_2, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_1, Col3PairType.TOP_3)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_3, Col3PairType.TOP_1));
		}});
		
		colPairsRelation.put(Col3PairType.BOTTOM_3, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_1, Col3PairType.TOP_2)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_2, Col3PairType.TOP_1));
		}});
		
		colPairsRelation.put(Col3PairType.BOTTOM_4, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_5, Col3PairType.TOP_6)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_6, Col3PairType.TOP_5));
		}});
		
		colPairsRelation.put(Col3PairType.BOTTOM_5, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_4, Col3PairType.TOP_6)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_6, Col3PairType.TOP_4));
		}});
		
		colPairsRelation.put(Col3PairType.BOTTOM_6, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_4, Col3PairType.TOP_5)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_5, Col3PairType.TOP_4));
		}});
		
		colPairsRelation.put(Col3PairType.BOTTOM_7, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_8, Col3PairType.TOP_9)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_9, Col3PairType.TOP_8));
		}});
		
		colPairsRelation.put(Col3PairType.BOTTOM_8, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_7, Col3PairType.TOP_9)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_9, Col3PairType.TOP_7));
		}});
		
		colPairsRelation.put(Col3PairType.BOTTOM_9, new ArrayList<OptionalRelationCol>() {{
			add(new OptionalRelationCol(Col3PairType.MIDDLE_7, Col3PairType.TOP_8)); 
			add(new OptionalRelationCol(Col3PairType.MIDDLE_8, Col3PairType.TOP_7));
		}});
	}
	
	public List<OptionalRelationCol> getOptionalRelationCol(Col3PairType col3PairType) {
		return colPairsRelation.get(col3PairType);
	}
}
