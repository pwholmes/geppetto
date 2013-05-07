package org.geppetto.domain.statement;

import org.geppetto.GeppettoProgram;

public class EndStatement implements Statement {

   public EndStatement() {
   }

   @Override
   public void execute() {
      GeppettoProgram.getInstance().setEndRequested(true);
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append("}");
      
      return sb.toString();
   }
   
}
