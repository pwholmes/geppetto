package org.geppetto.domain.statement;

import org.geppetto.domain.expression.Expression;

public class ExpressionStatement implements Statement {

   private Expression expression;

   protected ExpressionStatement() {
   }

   public ExpressionStatement(Expression expression) {
      this.expression = expression;
   }
}
