package org.geppetto.domain.statement;

import org.geppetto.domain.expression.Expression;

public class ExpressionStatement implements Statement {

   private Expression expression;

   protected ExpressionStatement() {
   }

   public ExpressionStatement(Expression expression) {
      this.expression = expression;
   }

   public Expression getExpression() {
      return expression;
   }

   @Override
   public void execute() {
      expression.getValue(); // just evaluate the expression, its value doesn't go anywhere (if it did, it would be an assignment in a BinaryExpression)
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("expression: ").append(getExpression());
      sb.append("}");
      
      return sb.toString();
   }
   
}
