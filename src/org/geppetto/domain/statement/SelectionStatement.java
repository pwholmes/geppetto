package org.geppetto.domain.statement;

import org.geppetto.domain.expression.BooleanExpression;

public class SelectionStatement implements Statement {

   private BooleanExpression booleanExpression;
   private Statement         ifStatement;
   private Statement         elseStatement;

   @SuppressWarnings("unused")
   private SelectionStatement() {
   }

   public SelectionStatement(BooleanExpression booleanExpression,
         Statement ifStatement) {
      this.booleanExpression = booleanExpression;
      this.ifStatement = ifStatement;
      elseStatement = null;
   }

   public SelectionStatement(BooleanExpression booleanExpression,
         Statement ifStatement, Statement elseStatement) {
      this.booleanExpression = booleanExpression;
      this.ifStatement = ifStatement;
      this.elseStatement = elseStatement;
   }

   public BooleanExpression getBooleanExpression() {
      return booleanExpression;
   }

   public Statement getIfStatement() {
      return ifStatement;
   }

   public Statement getElseStatement() {
      return elseStatement;
   }

   @Override
   public void execute() {
      // TODO Auto-generated method stub
      
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("expression: ").append(getBooleanExpression());
      sb.append("; ifStatement: ").append(getIfStatement());
      sb.append("; elseStatement: ").append(getElseStatement());
      sb.append("}");
      
      return sb.toString();
   }
   
}
