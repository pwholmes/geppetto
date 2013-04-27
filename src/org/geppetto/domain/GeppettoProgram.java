package org.geppetto.domain;

import java.util.ArrayList;

public class GeppettoProgram {
   private ArrayList<Variable> globalVariables;
   private ArrayList<Property> properties;
   private ArrayList<Entity>   entities;
   private ArrayList<Rule>     rules;

   public GeppettoProgram(ArrayList<Variable> globalVariables,
         ArrayList<Property> properties, ArrayList<Entity> entities,
         ArrayList<Rule> rules) {
      this.globalVariables = globalVariables;
      this.properties = properties;
      this.entities = entities;
      this.rules = rules;
   }

   public ArrayList<Variable> getGlobalVariables() {
      return globalVariables;
   }

   public ArrayList<Property> getProperties() {
      return properties;
   }

   public ArrayList<Entity> getEntities() {
      return entities;
   }

   public ArrayList<Rule> getRules() {
      return rules;
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
      
      sb.append("properties: {");
      first = true;
      for (Property property : properties) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(property);
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
