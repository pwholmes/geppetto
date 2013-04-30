package org.geppetto.expression;

public class FunctionExpression extends UnaryExpression {

   private PrimaryExpression      primaryExpression;
   private FunctionExpression     functionExpression;
   private ArgumentExpressionList argumentExpressionList;

   protected FunctionExpression() {
   }

   public FunctionExpression(PrimaryExpression primaryExpression) {
      this.primaryExpression = primaryExpression;
      this.functionExpression = null;
      this.argumentExpressionList = null;
   }

   public FunctionExpression(FunctionExpression functionExpression) {
      this.primaryExpression = null;
      this.functionExpression = functionExpression;
      this.argumentExpressionList = null;
   }

   public FunctionExpression(ArgumentExpressionList argumentExpressionList) {
      this.primaryExpression = null;
      this.functionExpression = null;
      this.argumentExpressionList = argumentExpressionList;
   }

   // public boolean evaluate() {
   // return true;
   // }
   //
   // public boolean checkSyntax() {
   // return true;
   // }
   //
   // public String toString() {
   // return this.toString();
   // }

   public void print() {
      System.out.println(toString());
   }
}
