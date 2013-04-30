package org.geppetto.expression;

public class StatementList extends Statement {

   private StatementList statementList;
   private Statement     statement;

   private StatementList() {
   }

   public StatementList(Statement statement) {
      statementList = null;
      this.statement = statement;
   }

   public StatementList(StatementList statementList, Statement statement) {
      this.statementList = statementList;
      this.statement = statement;
   }

   // public boolean execute() {
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
