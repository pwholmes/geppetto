package org.geppetto.expression;

public class ArgumentExpressionList extends FunctionExpression {

   private LogicalOrExpression    logicalOrExpression;
   private ArgumentExpressionList argumentExpressionList;

   private ArgumentExpressionList() {
   }

   public ArgumentExpressionList(LogicalOrExpression logicalOrExpression) {
      this.argumentExpressionList = null;
      this.logicalOrExpression = logicalOrExpression;
   }

   public ArgumentExpressionList(
         ArgumentExpressionList argumentExpressionList,
         LogicalOrExpression logicalOrExpression) {
      this.argumentExpressionList = argumentExpressionList;
      this.logicalOrExpression = logicalOrExpression;
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
