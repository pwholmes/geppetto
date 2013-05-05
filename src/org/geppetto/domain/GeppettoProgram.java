package org.geppetto.domain;

import java.util.ArrayList;
import org.geppetto.domain.expression.Variable;

public class GeppettoProgram {
   private static GeppettoProgram        instance;
   private ArrayList<Variable>           variables;
   private ArrayList<PropertyDefinition> propertyDefinitions;
   private ArrayList<Entity>             entities;
   private ArrayList<Rule>               rules;
   private ArrayList<FunctionDefinition> functionDefinitions;

   public static GeppettoProgram createInstance(ArrayList<Variable> variables,
         ArrayList<PropertyDefinition> propertyDefinitions,
         ArrayList<Entity> entities, ArrayList<Rule> rules,
         ArrayList<FunctionDefinition> functionDefinitions) {
      instance = new GeppettoProgram();
      instance.variables = variables;
      instance.propertyDefinitions = propertyDefinitions;
      instance.entities = entities;
      instance.rules = rules;
      instance.functionDefinitions = functionDefinitions;
      return instance;
   }

   public static GeppettoProgram getInstance() {
      return instance;
   }

   public ArrayList<Variable> getVariables() {
      return variables;
   }

   public Variable getVariable(String name) {
      for (Variable variable : getVariables()) {
         if (variable.getName().equals(name))
            return variable;
      }
      return null;
   }

   public ArrayList<PropertyDefinition> getPropertyDefinitions() {
      return propertyDefinitions;
   }

   public PropertyDefinition getPropertyDefinition(String name) {
      for (PropertyDefinition propertyDef : getPropertyDefinitions()) {
         if (propertyDef.getName().equals(name))
            return propertyDef;
      }
      return null;
   }

   public ArrayList<Entity> getEntities() {
      return entities;
   }

   public Entity getEntity(String name) {
      for (Entity entity : getEntities()) {
         if (entity.getName().equals(name))
            return entity;
      }
      return null;
   }

   public ArrayList<Rule> getRules() {
      return rules;
   }

   public ArrayList<FunctionDefinition> getFunctionDefinitions() {
      return functionDefinitions;
   }
   
   public FunctionDefinition getFunctionDefinition(String name) {
      for (FunctionDefinition functionDefinition : getFunctionDefinitions()) {
         if (functionDefinition.getName().equals(name))
            return functionDefinition;
      }
      return null;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");

      sb.append("variables: ").append(getVariables()).append("\n");
      sb.append("propertyDefinitions: ").append(getPropertyDefinitions())
            .append("\n");
      sb.append("entities: ").append(getEntities()).append("\n");
      sb.append("rules: ").append(getRules()).append("\n");
      sb.append("functionDefinitions: ").append(getFunctionDefinitions())
            .append("\n");
      sb.append("}");

      return sb.toString();
   }
}
