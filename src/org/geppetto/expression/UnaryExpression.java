package org.geppetto.expression;

public class UnaryExpression extends MultiplicativeExpression {

   private FunctionExpression functionExpression;
   private UnaryOperator unaryOperator;
   private Expression         expression;

   protected UnaryExpression() {
   }

   public UnaryExpression(FunctionExpression functionExpression) {
      this.functionExpression = functionExpression;
      unaryOperator = null;
      expression = null;
   }

   public UnaryExpression(UnaryOperator unaryOperator,
         Expression expression) {
      this.functionExpression = functionExpression;
      this.unaryOperator = unaryOperator;
      this.expression = expression;
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
