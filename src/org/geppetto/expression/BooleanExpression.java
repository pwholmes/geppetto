package org.geppetto.expression;

public class BooleanExpression extends Expression {

   private boolean bool;

   private BooleanExpression() {
   }

   public BooleanExpression(boolean bool) {
      this.bool = bool;
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
