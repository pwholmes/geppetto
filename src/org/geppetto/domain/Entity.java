package org.geppetto.domain;

import java.util.ArrayList;
import java.util.List;

public class Entity {
   String              name;
   ArrayList<Property> properties = new ArrayList<Property>();

   public Entity() {
   }

   public Entity(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void addProperty(Property property) {
      this.properties.add(property);
   }

   public void addProperties(List<Property> properties) {
      this.properties.addAll(properties);
   }

   public List<Property> getProperties() {
      return properties;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());

      sb.append("; properties: (");
      boolean first = true;
      for (Property property : properties) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(property);
      }
      sb.append(")}");

      return sb.toString();
   }
}
