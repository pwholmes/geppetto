package org.geppetto.domain.statement;

import java.util.ArrayList;

public class CompoundStatement implements Statement {
   private ArrayList<Statement> statements;

   @SuppressWarnings("unused")
   private CompoundStatement() {
   }
   
   public CompoundStatement(ArrayList<Statement> statements) {
      this.statements = statements;
   }
   
   public ArrayList<Statement> getStatements() {
      return statements;
   }

   @Override
   public boolean execute() {
      for (Statement statement : getStatements()) {
         if (!statement.execute())
            return false;
      }
      return true;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("statements: ").append(getStatements());
      sb.append("}");
      
      return sb.toString();
   }   
   
}
