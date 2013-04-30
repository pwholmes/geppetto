package org.geppetto.expression;

public class CompoundStatement extends Statement {

   private StatementList statementList;

   private CompoundStatement() {
   }

   public CompoundStatement(StatementList statementList) {
      this.statementList = statementList;
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

   public void print() {
      System.out.println(toString());
   }
}
