package org.geppetto.expression;

public class RelationalExpression extends EqualityExpression {

   private RelationalExpression relationalExpression;
   private RelationalOperator   relationalOperator;
   private AdditiveExpression   additiveExpression;

   protected RelationalExpression() {
   }

   public RelationalExpression(AdditiveExpression additiveExpressions) {
      this.relationalExpression = null;
      this.relationalOperator = null;
      this.additiveExpression = additiveExpression;
   }

   public RelationalExpression(RelationalExpression relationalExpression,
         RelationalOperator relationalOperator,
         AdditiveExpression additiveExpression) {
      this.relationalExpression = relationalExpression;
      this.relationalOperator = relationalOperator;
      this.additiveExpression = additiveExpression;
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
   //
   public void print() {
      System.out.println(toString());
   }
}
