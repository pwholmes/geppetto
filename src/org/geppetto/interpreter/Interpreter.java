package org.geppetto.interpreter;

import java.util.ArrayList;
import org.geppetto.domain.Entity;
import org.geppetto.domain.Property;
import org.geppetto.domain.Rule;

public class Interpreter {
   private ArrayList<Rule> rules;
   private ArrayList<Entity> entities;
   private ArrayList<Property> properties;
   private int maxCycles;
   private int cycles = 0; 
   
   public Interpreter(ArrayList<Rule> rules, ArrayList<Entity> entities, ArrayList<Property> properties, int maxCycles) {
      this.rules = rules;
      this.entities = entities;
      this.properties = properties;
      this.maxCycles = maxCycles;
   }
   
   public ArrayList<Rule> getRules() {
      return rules;
   }

   public ArrayList<Entity> getEntities() {
      return entities;
   }

   public ArrayList<Property> getProperties() {
      return properties;
   }

   public int getMaxCycles() {
      return maxCycles;
   }

   public int getCycles() {
      return cycles;
   }

   public void simulate() {
      for (Rule rule : rules) {
         processRule(rule);
         cycles++;
         if (cycles > maxCycles) {
            print("Maximum number of cycles reached, program terminating.");
            break;
         }
      }
   }
   
   public void processRule(Rule rule) {
      // If rule's condition is true, then execute rule
      // Q: How do we evaluate a non-boolean expression?
      // Q: Do we put the code to evaluate a rule here, or in the Rule class?  Does Rule have
      // everything it needs to evaluate its condition?  Obviously the answer is no -- it needs the
      // symbol table, and the list of property and entity definitions, or we wouldn't have them in the
      // first place. 
      // Condition may contain variables that correspond to entities
      
   }
   
   public void print(String s) {
      System.out.println(s);
   }
}
