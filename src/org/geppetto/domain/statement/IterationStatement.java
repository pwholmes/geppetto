package org.geppetto.domain.statement;

import org.geppetto.domain.expression.BooleanExpression;

public class IterationStatement implements Statement {

   private BooleanExpression booleanExpression;
   private Statement         statement;

   private IterationStatement() {
   }

   public IterationStatement(BooleanExpression booleanExpression, Statement statement) {
      this.booleanExpression = booleanExpression;
      this.statement = statement;
   }
}
