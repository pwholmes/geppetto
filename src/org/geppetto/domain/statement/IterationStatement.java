package org.geppetto.domain.statement;

import org.geppetto.GeppettoException;
import org.geppetto.domain.DataType;
import org.geppetto.domain.expression.Expression;

public class IterationStatement implements Statement {

   private Expression expression;
   private Statement  statement;

   @SuppressWarnings("unused")
   private IterationStatement() {
   }

   public IterationStatement(Expression expression, Statement statement) {
      this.expression = expression;
      this.statement = statement;
   }

   public Expression getExpression() {
      return expression;
   }

   public Statement getStatement() {
      return statement;
   }

   @Override
   public void execute() {
      if (expression.getValue().getType() != DataType.BOOLEAN)
         throw new GeppettoException("Iteration statement condition must be a boolean expression, but is of type: " + expression.getValue().getType());
      if (expression.getValue().getBooleanValue())
         statement.execute();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("expression: ").append(getExpression());
      sb.append("; statement: ").append(getStatement());
      sb.append("}");

      return sb.toString();
   }

}
