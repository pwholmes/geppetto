package org.geppetto.domain.statement;

import org.geppetto.domain.expression.Expression;
import org.geppetto.domain.expression.VariableType;

public class PrintStatement implements Statement {
   private Expression stringExpression;

   @SuppressWarnings("unused")
   private PrintStatement() {}
   
   public PrintStatement(Expression stringExpression) {
      if (stringExpression.getValue().getType() != VariableType.STRING)
         throw new IllegalArgumentException("Type mismatch: Argument to print statement must be string expression.");
      this.stringExpression = stringExpression;
   }
   
   public Expression getStringExpression() {
      return stringExpression;
   }

   @Override
   public void execute() {
      System.out.println(stringExpression.getValue().getsValue());
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("stringExpression: ").append(getStringExpression());
      sb.append("}");
      
      return sb.toString();
   }

}
