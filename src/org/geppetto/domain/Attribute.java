package org.geppetto.domain;

public class Attribute {
   private String       name;
   private VariableType type;
   public int           iValue;
   public float         fValue;
   public String        sValue;
   public boolean       bValue;

   @SuppressWarnings("unused")
   private Attribute() {
   }

   public Attribute(String name, int initializer) {
      type = VariableType.INT;
      iValue = initializer;
   }

   public Attribute(String name, float initializer) {
      type = VariableType.FLOAT;
      fValue = initializer;
   }

   public Attribute(String name, String initializer) {
      type = VariableType.STRING;
      sValue = initializer;
   }

   public Attribute(String name, boolean initializer) {
      type = VariableType.BOOLEAN;
      bValue = initializer;
   }

   public void setValue(int value) {
      if (type != VariableType.INT)
         throw new IllegalArgumentException();
      iValue = value;
   }

   public void setValue(float value) {
      if (type != VariableType.FLOAT)
         throw new IllegalArgumentException();
      fValue = value;
   }

   public void setValue(String value) {
      if (type != VariableType.STRING)
         throw new IllegalArgumentException();
      sValue = value;
   }

   public void setValue(boolean value) {
      if (type != VariableType.BOOLEAN)
         throw new IllegalArgumentException();
      bValue = value;
   }
   
   public String getName() {
      return name;
   }
   
   public int getIntValue() {
      return iValue;
   }
   
   public float getFloatValue() {
      return fValue;
   }

   public String getStringValue() {
      return sValue;
   }
   
   public boolean getBooleanValue() {
      return bValue;
   }
}
