package org.geppetto.domain;

import java.util.List;

public class AttributeConstraintIntegerRange implements AttributeConstraint {
   private int minValue;
   private int maxValue;
   
   public AttributeConstraintIntegerRange(int minValue, int maxValue) {
      this.minValue = minValue;
      this.maxValue = maxValue;
   }
   
   public AttributeConstraintIntegerRange(List<Integer> intList) {
      this.minValue = intList.get(0);
      this.maxValue = intList.get(1);
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
