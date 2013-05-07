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
   
   public int getIntValue() {
      if (type == VariableType.INT)
         return intValue;
      else if (type == VariableType.FLOAT)
         return (int)floatValue;
      else if (type == VariableType.STRING) {
         try {
            return Integer.valueOf(stringValue);
         } catch (Throwable e) {
            throw new GeppettoException("Cannot convert type " + getType() + " to type " + type + ".", e);
         }
      }
      else // (type == VariableType.BOOLEAN)
         throw new GeppettoException("Cannot convert type " + getType() + " to type " + type + ".");
   }

   public float getFloatValue() {
      if (type == VariableType.INT)
         return (float)intValue;
      else if (type == VariableType.FLOAT)
         return floatValue;
      else if (type == VariableType.STRING) {
         try {
            return Float.valueOf(stringValue);
         } catch (Throwable e) {
            throw new GeppettoException("Cannot convert type " + getType() + " to type " + type + ".", e);
         }
      }
      else // (type == VariableType.BOOLEAN)
         throw new GeppettoException("Cannot convert type " + getType() + " to type " + type + ".");
   }

   public String getStringValue() {
      if (type == VariableType.INT)
         return String.valueOf(intValue);
      else if (type == VariableType.FLOAT)
         return String.valueOf(floatValue);
      else if (type == VariableType.STRING)
         return stringValue;
      else // (type == VariableType.BOOLEAN)
         return String.valueOf(booleanValue);
   }

   /**
    * For now I'm allowing Int and Float types to be implicitly converted into Booleans (if != 0 then true, otherwise false).
    * I'm  not sure that's a good idea, though, so keep an eye on this...
    * TODO: Ensure it's OK to convert numerical types into booleans 
    */
   public boolean getBooleanValue() {
      if (type == VariableType.INT)
         return (intValue != 0);
      else if (type == VariableType.FLOAT)
         return (floatValue != 0);
      else if (type == VariableType.STRING)
         return Boolean.valueOf(stringValue);
      else // (type == VariableType.BOOLEAN)
         return booleanValue;
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
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("type: ").append(getType());
      sb.append("; intValue: ").append(intValue);
      sb.append("; floatValue: ").append(floatValue);
      sb.append("; stringValue: ").append(stringValue);
      sb.append("; booleanValue: ").append(booleanValue);
      sb.append("}");
      
      return sb.toString();
   }
}
