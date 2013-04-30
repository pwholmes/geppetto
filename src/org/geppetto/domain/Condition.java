package org.geppetto.domain;

import org.geppetto.domain.expression.BooleanExpression;

public class Condition {
   private BooleanExpression booleanExpression;
   
   @SuppressWarnings("unused")
   private Condition() {}

   public Condition(BooleanExpression booleanExpression) {
      this.booleanExpression = booleanExpression;
   }
   
   public BooleanExpression getBooleanExpression() {
      return booleanExpression;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("; booleanExpression: ");
      
      return sb.toString();
   }
}
