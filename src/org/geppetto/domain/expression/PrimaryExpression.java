package org.geppetto.domain.expression;

import org.geppetto.domain.Value;

public class PrimaryExpression implements Expression {
   private String name;
   private Value value;
   
   private PrimaryExpression() {}
   
   public PrimaryExpression(String name) {
      this.name = name;
   }
   
   public PrimaryExpression(Value value) {
      this.value = value;
   }
}
