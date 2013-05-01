package org.geppetto.domain.statement;

import org.geppetto.domain.expression.BooleanExpression;

public class IterationStatement implements Statement {

   private BooleanExpression booleanExpression;
   private Statement         statement;

   @SuppressWarnings("unused")
   private IterationStatement() {
   }

   public IterationStatement(BooleanExpression booleanExpression, Statement statement) {
      this.booleanExpression = booleanExpression;
      this.statement = statement;
   }

   public BooleanExpression getBooleanExpression() {
      return booleanExpression;
   }

   public Statement getStatement() {
      return statement;
   }

   @Override
   public void execute() {
      // TODO Auto-generated method stub
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("expression: ").append(getBooleanExpression());
      sb.append("; statement: ").append(getStatement());
      sb.append("}");
      
      return sb.toString();
   }
   
}
