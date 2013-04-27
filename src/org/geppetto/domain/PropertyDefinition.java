package org.geppetto.domain;

import java.util.ArrayList;
import java.util.List;

public class PropertyDefinition {
   public String                 name;
   private ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();

   @SuppressWarnings("unused")
   private PropertyDefinition() {
   }

   public PropertyDefinition(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }
   
   public void addAttributeDefinition(AttributeDefinition attributeDefinition) {
      attributeDefinitions.add(attributeDefinition);
   }
   
   public void addAttributeDefinitions(List<AttributeDefinition> attributeDefinitions) {
      this.attributeDefinitions.addAll(attributeDefinitions);
   }
   
   public List<AttributeDefinition> getAttributeDefinitions() {
      return attributeDefinitions;
   }
   
   public AttributeDefinition getAttributeDefinition(String attributeName) {
      for (AttributeDefinition attributeDef : attributeDefinitions) {
         if (attributeDef.getName().equals(attributeName))
            return attributeDef;
      }
      return null;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      
      sb.append("; attributes: {");
      boolean first = true;
      for (AttributeDefinition attribDef : getAttributeDefinitions()) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(attribDef);
      }
      sb.append("}; ");

      return sb.toString();
   }
   
}
