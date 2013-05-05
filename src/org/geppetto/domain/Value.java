package org.geppetto.domain;

import org.geppetto.domain.expression.VariableType;

public class Value {
   private VariableType type;
   private int iValue;
   private float fValue;
   private String sValue;
   private boolean bValue;
   
   protected Value() {}
   
   public Value(int value) {
      this.type = VariableType.INT;
      this.iValue = value;
   }

   public Value(float value) {
      this.type = VariableType.FLOAT;
      this.fValue = value;
   }
   
   public Value(String value) {
      this.type = VariableType.STRING;
      this.sValue = value;
   }

   public Value(boolean value) {
      this.type = VariableType.BOOLEAN;
      this.bValue = value;
   }

   public VariableType getType() {
      return this.type;
   }
   
   public void setType(VariableType type) {
      this.type = type;
   }
   
   public int getiValue() {
      return iValue;
   }

   public void setiValue(int iValue) {
      this.iValue = iValue;
   }

   public float getfValue() {
      return fValue;
   }

   public void setfValue(float fValue) {
      this.fValue = fValue;
   }

   public String getsValue() {
      return sValue;
   }

   public void setsValue(String sValue) {
      this.sValue = sValue;
   }

   public boolean getbValue() {
      return bValue;
   }

   public void setbValue(boolean bValue) {
      this.bValue = bValue;
   }
   
   public Value copy() {
      Value value = new Value();
      value.type = getType();
      value.bValue = getbValue();
      value.iValue = getiValue();
      value.fValue = getfValue();
      value.sValue = getsValue();
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
            return (value1.getbValue() == value2.getbValue());
         case FLOAT:
            return (value1.getfValue() == value2.getfValue());
         case INT:
            return (value1.getiValue() == value2.getiValue());
         case STRING:
            return (value1.getsValue() == value2.getsValue());
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
         throw new IllegalArgumentException("Cannot compare null values");
      
      if (value1.getType() != value2.getType())
         throw new IllegalArgumentException("Values being compared are not of the same type.");

      switch (value1.getType()) {
         case BOOLEAN:
            return Boolean.compare(value1.getbValue(), value2.getbValue());
         case FLOAT:
            return Float.compare(value1.getfValue(), value2.getfValue());
         case INT:
            return Integer.compare(value1.getiValue(), value2.getiValue());
         case STRING:
            return value1.getsValue().compareTo(value2.getsValue());
      }

      throw new IllegalArgumentException("Unknown data type: " + value1.getType()); // should never get here
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getType());
      sb.append("; iValue: ").append(getiValue());
      sb.append("; fValue: ").append(getfValue());
      sb.append("; sValue: ").append(getsValue());
      sb.append("; bValue: ").append(getbValue());
      sb.append("}");
      
      return sb.toString();
   }
}
