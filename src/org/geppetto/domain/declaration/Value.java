package org.geppetto.domain.declaration;

import org.geppetto.GeppettoException;
import org.geppetto.domain.expression.VariableType;

public class Value {
   private VariableType type;
   private int intValue = 0;
   private float floatValue = 0;
   private String stringValue = null;
   private boolean booleanValue = false;
   
   protected Value() {}
   
   public Value(int intValue) {
      this.type = VariableType.INT;
      this.intValue = intValue;
   }

   public Value(float floatValue) {
      this.type = VariableType.FLOAT;
      this.floatValue = floatValue;
   }
   
   public Value(String stringValue) {
      this.type = VariableType.STRING;
      this.stringValue = stringValue;
   }

   public Value(boolean booleanValue) {
      this.type = VariableType.BOOLEAN;
      this.booleanValue = booleanValue;
   }

   public VariableType getType() {
      return this.type;
   }
   
   public void setType(VariableType type) {
      this.type = type;
   }
   
   public int getIntValue() {
      return intValue;
   }

   public void setIntValue(int intValue) {
      this.intValue = intValue;
   }

   public float getFloatValue() {
      return floatValue;
   }

   public void setFloatValue(float floatValue) {
      this.floatValue = floatValue;
   }

   public String getStringValue() {
      return stringValue;
   }

   public void setStringValue(String stringValue) {
      this.stringValue = stringValue;
   }

   public boolean getBooleanValue() {
      return booleanValue;
   }

   public void setBooleanValue(boolean booleanValue) {
      this.booleanValue = booleanValue;
   }
   
   public Value copy() {
      Value value = new Value();
      value.type = getType();
      value.booleanValue = getBooleanValue();
      value.intValue = getIntValue();
      value.floatValue = getFloatValue();
      value.stringValue = getStringValue();
      return value;
   }
   
   public boolean equals(Value value) {
      return Value.equals(this, value);
   }
   
   public static boolean equals(Value value1, Value value2) {
      if (value1 == null || value2 == null)
         return false; // equating anything to null is false
      
      if (value1.getType() != value2.getType())
         return false; // they have to be the same type; VariableType is an enum so you can so this comparison with ==
      
      switch (value1.getType()) {
         case BOOLEAN:
            return (value1.getBooleanValue() == value2.getBooleanValue());
         case FLOAT:
            return (value1.getFloatValue() == value2.getFloatValue());
         case INT:
            return (value1.getIntValue() == value2.getIntValue());
         case STRING:
            return (value1.getStringValue() == value2.getStringValue());
      }
      return false; // should never get here
   }
   
   public int compareTo(Value value) {
      return Value.compare(this, value);
   }
   
   /**
    * Returns 0 if the values are equal, a value < 0 if value 1 < value2, and a value > 0 if value1 > value2.
    * For booleans, true > false.
    */
   public static int compare(Value value1, Value value2) {
      if (value1 == null || value2 == null)
         throw new GeppettoException("Cannot compare null values");
      
      if (value1.getType() != value2.getType())
         throw new GeppettoException("Values being compared are not of the same type.");

      switch (value1.getType()) {
         case BOOLEAN:
            return Boolean.compare(value1.getBooleanValue(), value2.getBooleanValue());
         case FLOAT:
            return Float.compare(value1.getFloatValue(), value2.getFloatValue());
         case INT:
            return Integer.compare(value1.getIntValue(), value2.getIntValue());
         case STRING:
            return value1.getStringValue().compareTo(value2.getStringValue());
      }

      throw new GeppettoException("Unknown data type: " + value1.getType()); // should never get here
   }
   
   public Value convertTo(VariableType type) {
      switch (getType()) {
         case BOOLEAN:
            if (type == VariableType.BOOLEAN)
               return new Value(getBooleanValue());
            else if (type == VariableType.STRING)
               return new Value(String.valueOf(getBooleanValue()));
            else
               throw new GeppettoException("Cannot convert type " + getType() + " to type " + type + ".");
         case FLOAT:
            if (type == VariableType.FLOAT)
               return new Value(getFloatValue());
            else if (type == VariableType.INT)
               return new Value((int)getFloatValue());
            else if (type == VariableType.STRING)
               return new Value(String.valueOf(getFloatValue()));
            else
               throw new GeppettoException("Cannot convert type " + getType() + " to type " + type + ".");
         case INT:
            if (type == VariableType.FLOAT)
               return new Value((float)getIntValue());
            else if (type == VariableType.INT)
               return new Value(getIntValue());
            else if (type == VariableType.STRING)
               return new Value(String.valueOf(getIntValue()));
            else
               throw new GeppettoException("Cannot convert type " + getType() + " to type " + type + ".");
         case STRING: // Note that the numerical conversions will throw a NumberFormatException if the string can't be converted to the desired type
            try {
               if (type == VariableType.BOOLEAN)
                  return new Value(Boolean.valueOf(getStringValue()));
               else if (type == VariableType.FLOAT)
                  return new Value(Float.valueOf(getStringValue()));
               else if (type == VariableType.INT)
                  return new Value(Integer.valueOf(getStringValue()));
               else // (type == VariableType.STRING)
                  return new Value(getStringValue());
            } catch (Throwable e) {
               throw new GeppettoException("Cannot convert type " + getType() + " to type " + type + ".", e);
            }
         default:
            throw new GeppettoException("Cannot convert type " + getType() + " to type " + type + ".");
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("type: ").append(getType());
      sb.append("; iValue: ").append(getIntValue());
      sb.append("; fValue: ").append(getFloatValue());
      sb.append("; sValue: ").append(getStringValue());
      sb.append("; bValue: ").append(getBooleanValue());
      sb.append("}");
      
      return sb.toString();
   }
}
