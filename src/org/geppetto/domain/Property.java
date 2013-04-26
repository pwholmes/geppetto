package org.geppetto.domain;

import java.util.ArrayList;
import java.util.List;

public class Property {
   public String                 name;
   private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
   private ArrayList<AttributeInitializer> attributeInitializers = new ArrayList<AttributeInitializer>();

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
   
   public void addAttributeInitializer(AttributeInitializer attributeInitializer) {
      attributeInitializers.add(attributeInitializer);
   }
   
   public void addAttributeInitializers(List<AttributeInitializer> attributeInitializers) {
      this.attributeInitializers.addAll(attributeInitializers);
   }
   
   public List<AttributeInitializer> getAttributeInitializers() {
      return attributeInitializers;
   }
   
}
