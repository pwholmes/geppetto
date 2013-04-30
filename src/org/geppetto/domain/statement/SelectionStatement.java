package org.geppetto.domain.statement;

import org.geppetto.domain.expression.BooleanExpression;

public class SelectionStatement implements Statement {

   private BooleanExpression booleanExpression;
   private Statement         ifStatement;
   private Statement         elseStatement;

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
   //
   public void print() {
      System.out.println(toString());
   }
}
