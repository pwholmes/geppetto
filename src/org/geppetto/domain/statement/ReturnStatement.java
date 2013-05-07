package org.geppetto.domain.statement;

import org.geppetto.GeppettoProgram;
import org.geppetto.domain.expression.Expression;

public class ReturnStatement implements Statement {
   private Expression expression;
   
   @SuppressWarnings("unused")
   private ReturnStatement() {
   }

   public ReturnStatement(Expression expression) {
      this.expression = expression;
   }
   
   public Expression getExpression() {
      return expression;
   }
   
   @Override
   public void execute() {
      GeppettoProgram.getInstance().getContexts().getLast().setReturnValue(expression.getValue());
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("expression: ").append(getExpression());
      sb.append("}");

      return sb.toString();
   }
}
