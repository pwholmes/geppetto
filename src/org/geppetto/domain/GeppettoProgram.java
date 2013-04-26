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

}
