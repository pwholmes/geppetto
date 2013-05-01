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
   
   public float getMinValue() {
      return minValue;
   }

   public float getMaxValue() {
      return maxValue;
   }

   public void setMinValue(float minValue) {
      this.minValue = minValue;
   }

   public void setMaxValue(float maxValue) {
      this.maxValue = maxValue;
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.FLOAT_RANGE;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Float.class)
         return true;
      return ((Float)value < minValue || (Float)value > maxValue);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("minValue: ").append(getMinValue());
      sb.append("; maxValue: ").append(getMaxValue());
      sb.append("}");
      
      return sb.toString();
   }
}
