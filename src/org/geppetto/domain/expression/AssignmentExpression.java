package org.geppetto.domain.expression;

public class AssignmentExpression implements Expression {
   private Expression lvalue;
   private Expression rvalue;

   @SuppressWarnings("unused")
   private AssignmentExpression() {
   }

   public AssignmentExpression(Expression lvalue, Expression rvalue) {
      this.lvalue = lvalue;
      this.rvalue = rvalue;
   }
}
