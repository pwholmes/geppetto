package org.geppetto.expression;

public class EqualityExpression extends LogicalAndExpression {

   private EqualityExpression   equalityExpression;
   private EqualityOperator   equalityOperator;
   private RelationalExpression relationalExpression;

   protected EqualityExpression() {
   }

   public EqualityExpression(RelationalExpression relationalExpression) {
      equalityExpression = null;
      equalityOperator = null;
      this.relationalExpression = relationalExpression;
   }

   public EqualityExpression(EqualityExpression equalityExpression,
         EqualityOperator equalityOperator,
         RelationalExpression relationalExpression) {
      this.equalityExpression = equalityExpression;
      this.equalityOperator = equalityOperator;
      this.relationalExpression = relationalExpression;
   }

   // public boolean evaluate() {
   //
   // }
   //
   // public boolean checkSyntax() {
   //
   // }
   //
   // public String toString() {
   //
   // }

   public void print() {
      System.out.println(toString());
   }
}
