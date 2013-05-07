package org.geppetto.domain.declaration;

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

   public int getMinValue() {
      return minValue;
   }

   public void setMinValue(int minValue) {
      this.minValue = minValue;
   }

   public int getMaxValue() {
      return maxValue;
   }

   public void setMaxValue(int maxValue) {
      this.maxValue = maxValue;
   }

   @Override
   public AttributeConstraintType getType() {
      return AttributeConstraintType.INT_RANGE;
   }
   
   @Override
   public boolean violatesConstraint(Object value) {
      if (value.getClass() != Integer.class)
         return true;
      return ((Integer)value < minValue || (Integer)value > maxValue);
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
