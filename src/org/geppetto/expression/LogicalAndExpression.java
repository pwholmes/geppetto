package org.geppetto.expression;

public class LogicalAndExpression extends LogicalOrExpression {

   private LogicalAndExpression logicalAndExpression;
   private boolean              logicalAndOperator;
   private EqualityExpression   equalityExpression;

   protected LogicalAndExpression() {
   }

   public LogicalAndExpression(EqualityExpression equalityExpression) {
      this.logicalAndExpression = null;
      this.logicalAndOperator = false;
      this.equalityExpression = equalityExpression;
   }

   public LogicalAndExpression(LogicalAndExpression logicalAndExpression,
         boolean logicalAndOperator, EqualityExpression equalityExpression) {
      this.logicalAndExpression = logicalAndExpression;
      this.logicalAndOperator = logicalAndOperator;
      this.equalityExpression = equalityExpression;
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
