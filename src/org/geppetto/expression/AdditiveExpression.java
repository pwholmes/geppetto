package org.geppetto.expression;

public class AdditiveExpression extends RelationalExpression {

	private MultiplicativeExpression multiplicativeExpression;
	private AdditiveOperator additiveOperator;
	private AdditiveExpression additiveExpression;

	protected AdditiveExpression() {
	}
	
	public AdditiveExpression(MultiplicativeExpression multiplicativeExpression) {
		this.multiplicativeExpression = multiplicativeExpression;
		this.additiveOperator = null;
		this.additiveExpression = null;
	}

	public AdditiveExpression(AdditiveExpression additiveExpression, AdditiveOperator additiveOperator, MultiplicativeExpression multiplicativeExpression) {
		this.additiveExpression = additiveExpression;
		this.additiveOperator = additiveOperator;
		this.multiplicativeExpression = multiplicativeExpression;
	}
	
//	public boolean evaluate() {
//		return true;
//	}
//	
//	public boolean checkSyntax() {
//		return true;
//	}
//	
//	public String toString() {
//		return this.toString();
//	}
	
	public void print() {
		System.out.println( toString() );
	}
}
