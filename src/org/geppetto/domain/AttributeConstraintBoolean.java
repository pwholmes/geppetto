package org.geppetto.domain;

public class AttributeConstraintBoolean implements AttributeConstraint {
   
   public AttributeConstraintBoolean() {
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Boolean.class)
         return true;
      return false;
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.BOOLEAN;
   }
}
