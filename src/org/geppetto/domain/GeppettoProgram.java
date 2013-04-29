package org.geppetto.domain;

import java.util.ArrayList;

public class GeppettoProgram {
   private ArrayList<Variable> globalVariables;
   private ArrayList<PropertyDefinition> propertyDefinitions;
   private ArrayList<Entity>   entities;
   private ArrayList<Rule>     rules;

   public GeppettoProgram(ArrayList<Variable> globalVariables,
         ArrayList<PropertyDefinition> propertyDefinitions, ArrayList<Entity> entities,
         ArrayList<Rule> rules) {
      this.globalVariables = globalVariables;
      this.propertyDefinitions = propertyDefinitions;
      this.entities = entities;
      this.rules = rules;
   }

   public ArrayList<Variable> getGlobalVariables() {
      return globalVariables;
   }

   public ArrayList<PropertyDefinition> getPropertyDefinitions() {
      return propertyDefinitions;
   }

   public ArrayList<Entity> getEntities() {
      return entities;
   }

   public ArrayList<Rule> getRules() {
      return rules;
   }
   
   public PropertyDefinition getPropertyDefinition(String propertyName) {
      for (PropertyDefinition propertyDef : propertyDefinitions) {
         if (propertyDef.getName().equals(propertyName))
            return propertyDef;
      }
      return null;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append(getClass().getSimpleName()).append(": ");
      
      sb.append("globalVariables: {");
      boolean first = true;
      for (Variable variable : globalVariables) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(variable);
      }
      sb.append("}\n");
      
      sb.append("propertyDefinitions: {");
      first = true;
      for (PropertyDefinition propertyDef : propertyDefinitions) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(propertyDef);
      }
      sb.append("}\n");
      
      sb.append("entities: {");
      first = true;
      for (Entity entity : entities) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(entity);
      }
      sb.append("}\n");
      
      sb.append("rules: {");
      first = true;
      for (Rule rule : rules) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(rule);
      }
      sb.append("}\n");
      
      return sb.toString();
   }
}
