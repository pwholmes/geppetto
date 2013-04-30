package org.geppetto.expression;

public class ExpressionStatement extends Statement {

   private Expression expression;

   protected ExpressionStatement() {
   }

   public ExpressionStatement(Expression expression) {
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
