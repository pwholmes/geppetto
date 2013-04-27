package org.geppetto.domain;

import java.util.ArrayList;
import java.util.List;

public class Property {
   private String               name;
   private PropertyDefinition   propertyDefinition;
   private ArrayList<Attribute> attributes = new ArrayList<Attribute>();

   @SuppressWarnings("unused")
   private Property() {
   }

   public Property(String name, PropertyDefinition propertyDefinition) {
      this.name = name;
      this.propertyDefinition = propertyDefinition;
   }

   public String getName() {
      return name;
   }

   public void addAttribute(Attribute attribute) {
      attributes.add(attribute);
   }

   public void addAttributes(List<Attribute> attributes) {
      this.attributes.addAll(attributes);
   }

   public List<Attribute> getAttributes() {
      return attributes;
   }

   public void setAttributeValues(List<AttributeInitializer> attributeInitializers) {
      for (AttributeInitializer initializer : attributeInitializers) {
         String attributeName = initializer.getName();
         AttributeDefinition attributeDef = propertyDefinition.getAttributeDefinition(attributeName);
         if (attributeDef == null)
            throw new IllegalArgumentException("Unknown attribute: " + attributeName);
         setAttributeValue(initializer, attributeDef);
      }
   }

   public void setAttributeValue(AttributeInitializer attributeInitializer, AttributeDefinition attributeDef) {
      Value value = attributeInitializer.getValue();
      if (value.getType() != attributeDef.getType())
         throw new IllegalArgumentException("Invalid attribute data type; is: " + value.getType() + ", must be : " + attributeDef.getType());
      // TODO: verify that value is within constraints
      Attribute attribute = new Attribute(attributeInitializer.getName(), attributeInitializer.getValue());
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());

      sb.append("; attributes: {");
      boolean first = true;
      for (Attribute attrib : getAttributes()) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(attrib);
      }
      sb.append("}; ");

      return sb.toString();
   }

}
