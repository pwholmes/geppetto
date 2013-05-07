package org.geppetto.domain.declaration;

import org.geppetto.domain.expression.VariableType;

public class VariableDeclaration {
   private VariableType type;
   private String name;
   private Value value;

   public VariableDeclaration(String name, VariableType type, Value value) {
      this.name = name;
      this.type = type;
      this.value = value;
   }

   public VariableDeclaration(String name, VariableType type) {
      this.name = name;
      this.type = type;
      this.value = new Value();
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

   public void setValue(Value value) {
      this.value = value;
   }
   
   public Value getValue() {
      return this.value;
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
