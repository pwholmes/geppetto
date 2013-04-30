package org.geppetto.expression;

public class SelectionStatement extends Statement {

   private BooleanExpression booleanExpression;
   private Statement         ifStatement;
   private boolean           containsElse;
   private Statement         elseStatement;

   private SelectionStatement() {
   }

   public SelectionStatement(BooleanExpression booleanExpression,
         Statement ifStatement) {
      this.booleanExpression = booleanExpression;
      this.ifStatement = ifStatement;
      elseStatement = null;
      containsElse = false;
   }

   public SelectionStatement(BooleanExpression booleanExpression,
         Statement ifStatement, boolean containsElse, Statement elseStatement) {
      this.booleanExpression = booleanExpression;
      this.ifStatement = ifStatement;
      this.containsElse = containsElse;
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
