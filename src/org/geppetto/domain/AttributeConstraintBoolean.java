package org.geppetto.domain;

public class AttributeConstraintBoolean implements AttributeConstraint {
   
   public AttributeConstraintBoolean() {
   }
   
   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.BOOLEAN;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Boolean.class)
         return true;
      return false;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("true or false, duh");
      
      return sb.toString();
   }
}
