package org.geppetto.domain.declaration;

import org.geppetto.domain.DataType;



public class AttributeConstraintBoolean implements AttributeConstraint {
   
   public AttributeConstraintBoolean() {
   }
   
   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.BOOLEAN;
   }
   
   @Override
   public boolean violatesConstraint(Value value) {
      if (value.getType() != DataType.BOOLEAN)
         return true;
      return false;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("true or false, duh");
      sb.append("}");
      
      return sb.toString();
   }
}
