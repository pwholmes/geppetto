package org.geppetto.domain;

import java.util.LinkedList;
import java.util.List;

public class Entity {
   String               name;
   LinkedList<Property> properties;

   public Entity() {}
   
   public Entity(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }

   public void addProperty(Property property) {
      properties.add(property);
   }
   
   public List<Property> getProperties() {
      return properties;
   }
}
