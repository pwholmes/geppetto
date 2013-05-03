package org.geppetto.domain;

import java.util.HashSet;
import java.util.Set;

public class AttributeConstraintFloatSet implements AttributeConstraint {
   private Set<Float> values = new HashSet<Float>();
   
   public AttributeConstraintFloatSet(Set<Float> values) {
      values.addAll(values);
   }
   
   public Set<Float> getValues() {
      return values;
   }

   public void addValue(Float value) {
      values.add(value);
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.FLOAT_SET;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Float.class)
         return true;
      return !(values.contains(value));
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("values: ").append(getValues());
      sb.append("}");
      
      return sb.toString();
   }   
}
