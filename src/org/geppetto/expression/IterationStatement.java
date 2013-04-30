package org.geppetto.expression;

public class IterationStatement extends Statement {

   private BooleanExpression booleanExpression;
   private Identifier        identifier;
   private Statement         statement;

   private IterationStatement() {
   }

   public IterationStatement(BooleanExpression booleanExpression,
         Statement statement) {
      this.booleanExpression = booleanExpression;
      this.statement = statement;
      identifier = null;
   }

   public IterationStatement(Identifier identifier, Statement statement) {
      booleanExpression = null;
      this.statement = statement;
      this.identifier = identifier;
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
