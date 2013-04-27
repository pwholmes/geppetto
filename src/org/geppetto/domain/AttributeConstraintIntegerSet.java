package org.geppetto.domain;

import java.util.HashSet;
import java.util.Set;

public class AttributeConstraintIntegerSet implements AttributeConstraint {
   private Set<Integer> values = new HashSet<Integer>();
   
   public AttributeConstraintIntegerSet(Set<Integer> values) {
      this.values.addAll(values);
   }
   
   public void addValue(Integer value) {
      this.values.add(value);
   }

   public Set<Integer> getValues() {
      return this.values;
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.INT_SET;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Integer.class)
         return true;
      return !(values.contains(value));
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("values: {");
      boolean first = true;
      for (Integer value : values) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(value);
      }
      sb.append("} ");
      
      return sb.toString();
   }      
}
