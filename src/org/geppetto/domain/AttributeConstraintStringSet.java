package org.geppetto.domain;

import java.util.Set;

public class AttributeConstraintStringSet implements AttributeConstraint {
   private Set<String> values = null;
   
   public AttributeConstraintStringSet(Set<String> values) {
      this.values = values;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != String.class)
         return true;
      return !(values.contains(value));
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.STRING_SET;
   }
}
