package org.geppetto.domain;

import java.util.List;

public class AttributeConstraintFloatRange implements AttributeConstraint {
   private float minValue;
   private float maxValue;
   
   public AttributeConstraintFloatRange(int minValue, int maxValue) {
      this.minValue = minValue;
      this.maxValue = maxValue;
   }

   public AttributeConstraintFloatRange(List<Float> floatList) {
      this.minValue = floatList.get(0);
      this.maxValue = floatList.get(1);
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Float.class)
         return true;
      return ((Float)value < minValue || (Float)value > maxValue);
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.FLOAT_RANGE;
   }
}
