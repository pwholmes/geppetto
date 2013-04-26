package org.geppetto.domain;

public class Variable {
   private VariableType type;
   private String name;
   private Object value;

   public Variable(VariableType type, String name, Object value) {
      this.type = type;
      this.name = name;
      this.value = value;
   }

   public VariableType getType() {
      return type;
   }

   public void setType(VariableType type) {
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Object getValue() {
      return value;
   }

   public void setValue(Object value) {
      this.value = value;
   }
}
