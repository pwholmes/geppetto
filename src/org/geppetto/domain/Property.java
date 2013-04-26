package org.geppetto.domain;

import java.util.LinkedList;
import java.util.List;

public class Property {
   public String                 name;
   private LinkedList<Attribute> attributes = new LinkedList<Attribute>();

   @SuppressWarnings("unused")
   private Property() {
   }

   public Property(String name) {
      this.name = name;
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
}
