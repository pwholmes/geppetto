package org.geppetto.domain.declaration;

import org.geppetto.domain.DataType;

public class VariableDeclaration {
   private DataType type;
   private String name;
   private Value value;

   public VariableDeclaration(String name, DataType type, Value value) {
      this.name = name;
      this.type = type;
      this.value = value.convertTo(type);
   }

   public VariableDeclaration(String name, DataType type) {
      this.name = name;
      this.type = type;
      this.value = new Value(type); // needs a "default" value
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public DataType getType() {
      return type;
   }

   public void setType(DataType type) {
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
