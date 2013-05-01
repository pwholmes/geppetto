package org.geppetto.domain.statement;

public class EndStatement implements Statement {

   public EndStatement() {
   }

   @Override
   public void execute() {
      // TODO Auto-generated method stub
      
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("}");
      
      return sb.toString();
   }
   
}
