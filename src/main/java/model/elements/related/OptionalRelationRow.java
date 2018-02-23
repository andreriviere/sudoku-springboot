package model.elements.related;

import model.enums.Row3PairType;

public class OptionalRelationRow {
	
	private Row3PairType relation1;
	private Row3PairType relation2;
	
	public OptionalRelationRow(Row3PairType relation1, Row3PairType relation2) {
		this.relation1 = relation1;
		this.relation2 = relation2;
	}

	public Row3PairType getRelation1() {
		return relation1;
	}

	public Row3PairType getRelation2() {
		return relation2;
	}
	
	
	
}
