package org.geppetto.expression;

public class Statement implements StatementInterface {

   private ExpressionStatement expressionStatement;
   private CompoundStatement   compoundStatement;
   private SelectionStatement  selectionStatement;
   private IterationStatement  iterationStatement;
   private EndStatement        endStatement;
   private Declaration         declaration;

   protected Statement() {
   }

   public Statement(ExpressionStatement expressionStatement) {
      this.expressionStatement = expressionStatement;
      compoundStatement = null;
      selectionStatement = null;
      iterationStatement = null;
      endStatement = null;
      declaration = null;
   }

   public Statement(CompoundStatement compoundStatement) {
      expressionStatement = null;
      this.compoundStatement = compoundStatement;
      selectionStatement = null;
      iterationStatement = null;
      endStatement = null;
      declaration = null;
   }

   public Statement(SelectionStatement selectionStatement) {
      expressionStatement = null;
      compoundStatement = null;
      this.selectionStatement = selectionStatement;
      iterationStatement = null;
      endStatement = null;
      declaration = null;
   }

   public Statement(IterationStatement iterationStatement) {
      expressionStatement = null;
      compoundStatement = null;
      selectionStatement = null;
      this.iterationStatement = iterationStatement;
      endStatement = null;
      declaration = null;
   }

   public Statement(EndStatement endStatement) {
      expressionStatement = null;
      compoundStatement = null;
      selectionStatement = null;
      iterationStatement = null;
      this.endStatement = endStatement;
      declaration = null;
   }

   public Statement(Declaration declaration) {
      expressionStatement = null;
      compoundStatement = null;
      selectionStatement = null;
      iterationStatement = null;
      this.endStatement = null;
      this.declaration = declaration;
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
