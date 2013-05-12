package org.geppetto;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import org.geppetto.domain.declaration.Entity;
import org.geppetto.domain.declaration.FunctionDefinition;
import org.geppetto.domain.declaration.PropertyDefinition;
import org.geppetto.domain.declaration.Rule;
import org.geppetto.domain.declaration.VariableDeclaration;

public class GeppettoProgram {
   private static GeppettoProgram        instance;
   private LinkedList<ProgramContext>    contexts; // this one is a LinkedList because we want to be able to get a descendingIterator
   private ArrayList<PropertyDefinition> propertyDefinitions;
   private ArrayList<Entity>             entities;
   private ArrayList<Rule>               rules;
   private ArrayList<FunctionDefinition> functionDefinitions;
   private boolean                       debug;
   private boolean                       endRequested;

   public static GeppettoProgram createInstance(ArrayList<VariableDeclaration> globalVariables, ArrayList<PropertyDefinition> propertyDefinitions,
         ArrayList<Entity> entities, ArrayList<Rule> rules, ArrayList<FunctionDefinition> functionDefinitions) {
      instance = new GeppettoProgram();
      instance.contexts = new LinkedList<ProgramContext>();
      instance.contexts.add(new ProgramContext("global", globalVariables));
      instance.propertyDefinitions = propertyDefinitions;
      instance.entities = entities;
      instance.rules = rules;
      instance.functionDefinitions = functionDefinitions;
      return instance;
   }

   public static GeppettoProgram getInstance() {
      return instance;
   }

   public Deque<ProgramContext> getContexts() {
      return contexts;
   }

   /**
    * Loop through all program contexts, starting with the most recent, and within
    * each context loop through the list of variables, to find the variable with the 
    * name we're searching for.
    */
   public VariableDeclaration getVariableDeclaration(String name) {
      Iterator<ProgramContext> i = contexts.descendingIterator();
      while (i.hasNext()) {
         ProgramContext context = i.next();
         for (VariableDeclaration variable : context.getVariableDeclarations()) {
            if (variable.getName().equals(name))
               return variable;
         }
      }
      return null;
   }

   /**
    * Search for a variable in the global context only.
    */
   public VariableDeclaration getGlobalVariableDeclaration(String name) {
      ProgramContext context = contexts.getFirst();
      for (VariableDeclaration variable : context.getVariableDeclarations()) {
         if (variable.getName().equals(name))
            return variable;
      }
      return null;
   }

   /**
    * Add a variable to the global context.
    * This is only used for manually setting the "cycle" and "maxCycles" variables.
    */
   public void addGlobalVariableDeclaration(VariableDeclaration declaration) {
      ProgramContext context = contexts.getFirst();
      context.getVariableDeclarations().add(declaration);
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

   public void setDebug(boolean debug) {
      this.debug = debug;
   }

   public boolean isDebug() {
      return debug;
   }

   public void setEndRequested(boolean endRequested) {
      this.endRequested = endRequested;
   }

   public boolean isEndRequested() {
      return endRequested;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");

      sb.append("contexts: ").append(getContexts()).append("\n");
      sb.append("propertyDefinitions: ").append(getPropertyDefinitions()).append("\n");
      sb.append("entities: ").append(getEntities()).append("\n");
      sb.append("rules: ").append(getRules()).append("\n");
      sb.append("functionDefinitions: ").append(getFunctionDefinitions()).append("\n");
      sb.append("}");

      return sb.toString();
   }
}
