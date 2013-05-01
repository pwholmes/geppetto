package org.geppetto.domain;

import org.geppetto.domain.expression.Expression;

public class Condition {
   /* private BooleanExpression booleanExpression; */
   private Expression expression;
   
   @SuppressWarnings("unused")
   private Condition() {}

   public Condition(Expression expression) {
      this.expression = expression;
   }
   
   public Expression getExpression() {
      return expression;
   }

      /**
   public Condition(BooleanExpression booleanExpression) {
      this.booleanExpression = booleanExpression;
   }
   
   public BooleanExpression getBooleanExpression() {
      return booleanExpression;
   }
   **/
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("expression: ").append(getExpression());
      sb.append("}");
      
      return sb.toString();
   }
}
