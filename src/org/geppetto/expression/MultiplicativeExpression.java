package org.geppetto.expression;

public class MultiplicativeExpression extends AdditiveExpression {

   private UnaryExpression          unaryExpression;
   private MultiplicativeOperator multiplicativeOperator;
   private MultiplicativeExpression multiplicativeExpression;

   protected MultiplicativeExpression() {
   }

   public MultiplicativeExpression(UnaryExpression unaryExpression) {
      this.unaryExpression = unaryExpression;
      multiplicativeOperator = null;
      this.multiplicativeExpression = null;
   }

   public MultiplicativeExpression(
         MultiplicativeExpression multiplicativeExpression,
         MultiplicativeOperator multiplicativeOperator,
         UnaryExpression unaryExpression) {
      this.multiplicativeExpression = multiplicativeExpression;
      this.multiplicativeOperator = multiplicativeOperator;
      this.unaryExpression = unaryExpression;
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
