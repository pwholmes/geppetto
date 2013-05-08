package org.geppetto.domain.expression;

import org.geppetto.domain.declaration.Value;

public class VariantExpression implements Expression {
   private String name;
   private String propertyName;
   private String attributeName;

   public VariantExpression(String name, String propertyName, String attributeName) {
      this.name = name;
      this.propertyName = propertyName;
      this.attributeName = attributeName;
   }

   public VariantExpression(String name, String propertyName) {
      this.name = name;
      this.propertyName = propertyName;
   }

   public String getName() {
      return name;
   }

   public String getPropertyName() {
      return propertyName;
   }

   public String getAttributeName() {
      return attributeName;
   }

   @Override
   public boolean isLValue() {
      return true;
   }

   @Override
   public void setValue(Value value) {
      // TODO: setValue() for VariantExpression 
   }
   
   @Override
   public Value getValue() {
      // TODO: getValue() for VariantExpression 
      return null;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; propertyName: ").append(getPropertyName());
      sb.append("; attributeName: ").append(getAttributeName());
      sb.append("}");
      
      return sb.toString();
   }

}
