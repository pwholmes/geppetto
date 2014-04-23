package org.geppetto.domain.statement;

import org.geppetto.domain.expression.Expression;

public class PrintStatement implements Statement {
   private Expression stringExpression;

   @SuppressWarnings("unused")
   private PrintStatement() {}
   
   public PrintStatement(Expression stringExpression) {
      this.stringExpression = stringExpression;
   }
   
   public Expression getStringExpression() {
      return stringExpression;
   }

   @Override
   public void execute() {
      System.out.println(stringExpression.getValue().getStringValue());
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("stringExpression: ").append(getStringExpression());
      sb.append("}");
      
      return sb.toString();
   }

}
