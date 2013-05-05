package org.geppetto.domain.expression;

import org.geppetto.domain.Value;


public class Variant implements Expression {

   private VariableType type;
   private String name;
   private Value value;

   public Variant(String name, VariableType type, Value value) {
      this.name = name;
      this.type = type;
      this.value = value;
   }

   public Variant(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public VariableType getType() {
      return type;
   }

   public void setType(VariableType type) {
      this.type = type;
   }

   @Override
   public boolean isLValue() {
      return true;
   }

   @Override
   public void setValue(Value value) {
      this.value = value;
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
      sb.append("; type: ").append(getType());
      sb.append("; value: ").append(getValue());
      sb.append("}");
      
      return sb.toString();
   }

}
