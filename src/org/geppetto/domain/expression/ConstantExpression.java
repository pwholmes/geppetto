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

   @Override
   public boolean isLValue() {
      return false;
   }

   @Override
   public void setValue(Value value) {
      throw new IllegalArgumentException("Cannot assign a value to this expression");
   }    

   @Override
   public Value getValue() {
      return value;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; value: ").append(getValue());
      sb.append("}");
      
      return sb.toString();
   }

}
