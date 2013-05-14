package org.geppetto.domain.expression;

import org.geppetto.GeppettoException;
import org.geppetto.GeppettoProgram;
import org.geppetto.domain.declaration.Attribute;
import org.geppetto.domain.declaration.Entity;
import org.geppetto.domain.declaration.Property;
import org.geppetto.domain.declaration.Value;

public class EntityExpression implements Expression {
   private String entityName;
   private String propertyName;
   private String attributeName;

   @SuppressWarnings("unused")
   private EntityExpression() {
   }

   public EntityExpression(String entityName, String propertyName) {
      this.entityName = entityName;
      this.propertyName = propertyName;
   }

   public EntityExpression(String entityName, String propertyName, String attributeName) {
      this.entityName = entityName;
      this.propertyName = propertyName;
      this.attributeName = attributeName;
   }

   public String getEntityName() {
      return entityName;
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
      getAttribute().setValue(value);
   }

   @Override
   public Value getValue() {
      return getAttribute().getValue();
   }
   
   private Attribute getAttribute() {
      Entity entity = GeppettoProgram.getInstance().getEntity(getEntityName());
      if (entity == null)
         throw new GeppettoException("Undeclared entity: " + getEntityName() + ".");
      
      Property property = entity.getProperty(getPropertyName());
      if (property == null)
         throw new GeppettoException("Entity " + getEntityName() + " missing property: " + getPropertyName() + ".");

      Attribute attribute = null;
      if (attributeName == null) {
         if (property.getAttributes().size() == 1)
            attribute = property.getAttributes().get(0);
         else
            throw new GeppettoException("Property " + propertyName + " has more than one attribute, but no attribute name was specified.");
      } else {
         attribute = property.getAttribute(getAttributeName());
         if (attribute == null)
            throw new GeppettoException("Property " + getPropertyName() + " missing attribute: " + getAttributeName() + ".");
      }

      return attribute;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("entityName: ").append(getEntityName());
      sb.append("; propertyName: ").append(getPropertyName());
      sb.append("; attributeName: ").append(getAttributeName());
      sb.append("}");

      return sb.toString();
   }

}
