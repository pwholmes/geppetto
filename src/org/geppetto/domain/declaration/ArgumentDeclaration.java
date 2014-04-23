package org.geppetto.domain.declaration;

import org.geppetto.domain.DataType;

public class ArgumentDeclaration {
   /* name is irrelevant in a declaration list but it may be useful for debugging purposes */
   String name;
   DataType type;
   
   @SuppressWarnings("unused")
   private ArgumentDeclaration() {}
   
   public ArgumentDeclaration(String name, DataType type) {
      this.name = name;
      this.type = type;
   }
   
   public String getName() {
      return name;
   }

   public DataType getType() {
      return type;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; type: ").append(getType());
      sb.append("}");
      
      return sb.toString();
   }
}
