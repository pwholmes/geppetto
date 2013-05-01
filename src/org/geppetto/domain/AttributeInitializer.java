package org.geppetto.domain;

public class AttributeInitializer {
   public String name;
   public Value value;
   
   public AttributeInitializer(String name, Value value) {
      this.name = name;
      this.value = value;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Value getValue() {
      return value;
   }

   public void setInitialValue(Value value) {
      this.value = value;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; initialValue: ").append(getValue());
      sb.append("}");
      
      return sb.toString();
   }
}
