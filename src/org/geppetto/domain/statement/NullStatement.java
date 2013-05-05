package org.geppetto.domain.statement;

public class NullStatement implements Statement {

   @Override
   public void execute() {
      // TODO execute() for NullStatement

   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("}");
      
      return sb.toString();
   }   
}
