package org.geppetto.domain;

public class AttributeConstraintIntegerRange implements AttributeConstraint {
   private int minValue;
   private int maxValue;
   
   public AttributeConstraintIntegerRange(int minValue, int maxValue) {
      this.minValue = minValue;
      this.maxValue = maxValue;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Integer.class)
         return true;
      return ((Integer)value < minValue || (Integer)value > maxValue);
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.INT_RANGE;
   }
}
