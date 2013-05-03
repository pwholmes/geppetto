package org.geppetto.domain;

import java.util.ArrayList;
import org.geppetto.domain.expression.Variable;

public class GeppettoProgram {
   private ArrayList<Variable>           globalVariables;
   private ArrayList<PropertyDefinition> propertyDefinitions;
   private ArrayList<Entity>             entities;
   private ArrayList<Rule>               rules;
   private ArrayList<FunctionDefinition> functionDefinitions;

   public GeppettoProgram(ArrayList<Variable> globalVariables,
         ArrayList<PropertyDefinition> propertyDefinitions,
         ArrayList<Entity> entities, ArrayList<Rule> rules,
         ArrayList<FunctionDefinition> functionDefinitions) {
      this.globalVariables = globalVariables;
      this.propertyDefinitions = propertyDefinitions;
      this.entities = entities;
      this.rules = rules;
      this.functionDefinitions = functionDefinitions;
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

   public ArrayList<FunctionDefinition> getFunctionDefinitions() {
      return functionDefinitions;
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

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");

      sb.append("globalVariables: ").append(getGlobalVariables()).append("\n");
      sb.append("propertyDefinitions: ").append(getPropertyDefinitions()).append("\n");
      sb.append("entities: ").append(getEntities()).append("\n");
      sb.append("rules: ").append(getRules()).append("\n");
      sb.append("functionDefinitions: ").append(getFunctionDefinitions()).append("\n");
      sb.append("}");

      return sb.toString();
   }
}
