package org.geppetto.domain;

import org.geppetto.domain.expression.VariableType;

public class Value {
   VariableType type;
   int iValue;
   float fValue;
   String sValue;
   boolean bValue;
   
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
