package org.geppetto.domain.expression;

import org.geppetto.domain.Value;


public class Variable implements Expression {
   private VariableType type;
   private String name;
   private Value value;

   public Variable(String name, VariableType type, Value value) {
      this.name = name;
      this.type = type;
      this.value = value;
   }

   public Variable(String name) {
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

   public Object getValue() {
      return value;
   }

   public void setValue(Value value) {
      this.value = value;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; type: ").append(getType());
      sb.append("; value: ").append(getValue());
      sb.append("}");
      
      return sb.toString();
   }

   @Override
   public Value evaluate() {
      return value;
   }   
}
