package org.geppetto.domain;

import org.geppetto.domain.statement.Statement;

public class Behavior {
   private Statement statement;
   
   @SuppressWarnings("unused")
   private Behavior() {}
   
   public Behavior(Statement statement) {
      this.statement = statement;
   }
   
   public Statement getStatement() {
      return statement;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("; statement: ").append(getStatement());
      
      return sb.toString();
   }
}
