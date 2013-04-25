package org.geppetto.domain;

import java.util.Set;

public class AttributeConstraintIntegerSet implements AttributeConstraint {
   private Set<Integer> values = null;
   
   public AttributeConstraintIntegerSet(Set<Integer> values) {
      this.values = values;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Integer.class)
         return true;
      return !(values.contains(value));
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.INT_SET;
   }
}
