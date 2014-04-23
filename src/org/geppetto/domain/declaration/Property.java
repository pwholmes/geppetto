package org.geppetto.domain.declaration;

import java.util.ArrayList;
import java.util.List;
import org.geppetto.GeppettoException;

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
   
   public Attribute getAttribute(String name) {
      for (Attribute attribute : getAttributes()) {
         if (attribute.getName().equals(name))
            return attribute;
      }
      return null;
   }

   public void setAttributeValues(List<AttributeInitializer> attributeInitializers) {
      for (AttributeInitializer initializer : attributeInitializers) {
         String attributeName = initializer.getName();
         AttributeDefinition attributeDef = propertyDefinition.getAttributeDefinition(attributeName);
         if (attributeDef == null)
            throw new GeppettoException("Unknown attribute: " + attributeName);
         setAttributeValue(initializer, attributeDef);
      }
   }

   public void setAttributeValue(AttributeInitializer attributeInitializer, AttributeDefinition attributeDef) {
      Value value = attributeInitializer.getValue();
      if (value.getType() != attributeDef.getType())
         throw new GeppettoException("Invalid attribute data type; is: " + value.getType() + ", must be : " + attributeDef.getType());
      if (attributeDef.getConstraint().violatesConstraint(value))
         throw new GeppettoException("Constraint violation on attribute: " + attributeDef.getName() + ", value: " + value);
      Attribute attribute = new Attribute(attributeInitializer.getName(), attributeInitializer.getValue());
      attributes.add(attribute);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; attributes: ").append(getAttributes());
      sb.append("}");

      return sb.toString();
   }

}
