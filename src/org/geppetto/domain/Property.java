package org.geppetto.domain;

import java.util.LinkedList;

public class Property {
   public String name;
   private LinkedList<Attribute> attributes;
   
   @SuppressWarnings("unused")
   private Property() {}
   
   public Property(String name) {
      this.name = name;
   }
}
