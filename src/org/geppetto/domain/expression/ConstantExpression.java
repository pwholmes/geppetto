package org.geppetto.domain.expression;

import org.geppetto.domain.Value;

public class ConstantExpression implements Expression {
   private String name;
   private Value value;
   
   @SuppressWarnings("unused")
   private ConstantExpression() {}
   
   public ConstantExpression(String name) {
      this.name = name;
   }
   
   public ConstantExpression(Value value) {
      this.value = value;
   }

   public String getName() {
      return name;
   }

   public Value getValue() {
      return value;
   }

   @Override
   public Value evaluate() {
      // TODO Auto-generated method stub
      return null;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; value: ").append(getValue());
      sb.append("}");
      
      return sb.toString();
   }    
}
