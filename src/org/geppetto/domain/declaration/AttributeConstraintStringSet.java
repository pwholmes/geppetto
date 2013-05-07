package org.geppetto.domain.declaration;

import java.util.HashSet;
import java.util.Set;

public class AttributeConstraintStringSet implements AttributeConstraint {
   private Set<String> values = new HashSet<String>();
   
   public AttributeConstraintStringSet(Set<String> values) {
      this.values.addAll(values);
   }
   
   public void addValue(String value) {
      this.values.add(value);
   }

   public Set<String> getValues() {
      return this.values;
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.STRING_SET;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != String.class)
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
