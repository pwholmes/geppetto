package org.geppetto.domain.expression;

import org.geppetto.domain.Value;

public class BooleanExpression implements Expression {
   private Expression expression;
   
   @SuppressWarnings("unused")
   private BooleanExpression() {}
   
   public BooleanExpression(Expression expression) {
      this.expression = expression;
   }
   
   public Expression getExpression() {
      return expression;
   }

   @Override
   public Value evaluate() {
      // TODO Auto-generated method stub
      return null;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("expression: ").append(getExpression());
      sb.append("}");
      
      return sb.toString();
   }   
   
}
