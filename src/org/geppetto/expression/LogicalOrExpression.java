package org.geppetto.expression;

public class LogicalOrExpression extends AssignmentExpression {

   private LogicalOrExpression  logicalOrExpression;
   private boolean              logicalOrOperator;
   private LogicalAndExpression logicalAndExpression;

   protected LogicalOrExpression() {
   }

   public LogicalOrExpression(LogicalAndExpression logicalAndExpression) {
      this.logicalOrExpression = null;
      this.logicalOrOperator = false;
      this.logicalAndExpression = logicalAndExpression;
   }

   public LogicalOrExpression(LogicalOrExpression logicalOrExpression,
         boolean logicalOrOperator, LogicalAndExpression logicalAndExpression) {
      this.logicalOrExpression = logicalOrExpression;
      this.logicalOrOperator = logicalOrOperator;
      this.logicalAndExpression = logicalAndExpression;
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
