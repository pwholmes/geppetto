package org.geppetto.expression;

public class AssignmentExpression extends Expression {

   private LogicalOrExpression  logicalOrExpression;
   private UnaryExpression      unaryExpression;
   private boolean              assignmentOperator;
   private AssignmentExpression assignmentExpression;

   protected AssignmentExpression() {
   }

   public AssignmentExpression(LogicalOrExpression logicalOrExpression) {
      this.logicalOrExpression = logicalOrExpression;
      this.unaryExpression = null;
      this.assignmentOperator = false;
      this.assignmentExpression = null;
   }

   public AssignmentExpression(UnaryExpression unaryExpression,
         boolean assignmentOperator, AssignmentExpression assignmentExpression) {
      this.logicalOrExpression = null;
      this.unaryExpression = unaryExpression;
      this.assignmentOperator = assignmentOperator;
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
