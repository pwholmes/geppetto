package org.geppetto.domain;

import java.util.Set;

public class AttributeConstraintFloatSet implements AttributeConstraint {
   private Set<Float> values = null;
   
   public AttributeConstraintFloatSet(Set<Float> values) {
      this.values = values;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Float.class)
         return true;
      return !(values.contains(value));
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.FLOAT_SET;
   }
}
