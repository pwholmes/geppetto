package org.geppetto.domain.statement;

import org.geppetto.GeppettoException;
import org.geppetto.domain.DataType;
import org.geppetto.domain.expression.Expression;

public class SelectionStatement implements Statement {

   private Expression expression;
   private Statement  ifStatement;
   private Statement  elseStatement;

   @SuppressWarnings("unused")
   private SelectionStatement() {
   }

   public SelectionStatement(Expression expression, Statement ifStatement) {
      this.expression = expression;
      this.ifStatement = ifStatement;
      elseStatement = null;
   }

   public SelectionStatement(Expression booleanExpression,
         Statement ifStatement, Statement elseStatement) {
      this.expression = booleanExpression;
      this.ifStatement = ifStatement;
      this.elseStatement = elseStatement;
   }

   public Expression getExpression() {
      return expression;
   }

   public Statement getIfStatement() {
      return ifStatement;
   }

   public Statement getElseStatement() {
      return elseStatement;
   }

   @Override
   public void execute() {
      if (expression.getValue().getType() != DataType.BOOLEAN)
         throw new GeppettoException("Iteration statement condition must be a boolean expression, but is of type: " + expression.getValue().getType());
      if (expression.getValue().getBooleanValue())
         ifStatement.execute();
      else if (elseStatement != null)
         elseStatement.execute();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("expression: ").append(getExpression());
      sb.append("; ifStatement: ").append(getIfStatement());
      sb.append("; elseStatement: ").append(getElseStatement());
      sb.append("}");

      return sb.toString();
   }

}
