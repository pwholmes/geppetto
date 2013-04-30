package org.geppetto.expression;

public class Expression extends ExpressionStatement implements
      ExpressionInterface {

   private AssignmentExpression assignmentExpression;

   protected Expression() {
   }

   public Expression(AssignmentExpression assignmentExpression) {
      this.assignmentExpression = assignmentExpression;
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
