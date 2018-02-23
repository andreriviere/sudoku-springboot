package model.elements.related;

import model.enums.Col3PairType;

public class OptionalRelationCol {
	
	private Col3PairType relation1;
	private Col3PairType relation2;
	
	public OptionalRelationCol(Col3PairType relation1, Col3PairType relation2) {
		this.relation1 = relation1;
		this.relation2 = relation2;
	}

	public Col3PairType getRelation1() {
		return relation1;
	}

	public Col3PairType getRelation2() {
		return relation2;
	}
	
	
	
}
