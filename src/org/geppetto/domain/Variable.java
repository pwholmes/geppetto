package org.geppetto.domain;

public class Variable {
   private VariableType type;
   private String name;
   private Object value;

   public Variable(VariableType type, String name, Object value) {
      this.name = name;
      this.type = type;
      this.value = value;
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

   public void setValue(Object value) {
      this.value = value;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; type: ").append(getType());
      sb.append("; value: ").append(getValue());
      
      return sb.toString();
   }   
}
