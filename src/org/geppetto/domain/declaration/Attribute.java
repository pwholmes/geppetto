package org.geppetto.domain.declaration;




public class Attribute {
   private String              name;
   private Value              value;

   protected Attribute() {
   }

   public Attribute(String name, Value value) {
      this.name = name;
      this.value = value;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setValue(Value value) {
      this.value = value;
   }

   public Value getValue() {
      return value;
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
