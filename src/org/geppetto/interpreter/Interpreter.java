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
      
   }
   
   public void print(String s) {
      System.out.println(s);
   }
}
