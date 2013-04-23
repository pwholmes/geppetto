package org.geppetto.domain;

public class Attribute {
   private VariableType type;
   public int           iValue;
   public float         fValue;
   public String        sValue;
   public boolean       bValue;
   
   @SuppressWarnings("unused")
   private Attribute() {}
   
   public Attribute(int initializer) {
      type = VariableType.INT;
      iValue = initializer;
   }
   
   public Attribute(float initializer) {
      type = VariableType.FLOAT;
      fValue = initializer;
   }
   public Attribute(String initializer) {
      type = VariableType.STRING;
      sValue = initializer;
   }
   public Attribute(boolean initializer) {
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
}
