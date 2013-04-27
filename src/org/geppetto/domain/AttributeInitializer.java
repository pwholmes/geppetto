package org.geppetto.domain;

public class AttributeInitializer {
   public String name;
   public Object initialValue;
   
   public AttributeInitializer(String name, Object initialValue) {
      this.name = name;
      this.initialValue = initialValue;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Object getInitialValue() {
      return initialValue;
   }

   public void setInitialValue(Object initialValue) {
      this.initialValue = initialValue;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; initialValue: ").append(getInitialValue());
      
      return sb.toString();
   }
}
