package org.geppetto.domain;

public class AttributeInitializer {
   public String name;
   public Object initialValue;
   
   public AttributeInitializer(String name, Object initialValue) {
      this.name = name;
      this.initialValue = initialValue;
   }
}
