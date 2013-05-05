package org.geppetto.interpreter;

import org.geppetto.domain.GeppettoProgram;
import org.geppetto.domain.Rule;

public class Interpreter {
   private boolean debug;
   private int maxCycles = 100;
   private int cycles = 0; 
   
   public Interpreter(boolean debug) {
      this.debug = debug;
   }
   
   public void setDebug(boolean debug) {
      this.debug = debug;
   }

   public boolean isDebug() {
      return debug;
   }

   public void setMaxCycles(int maxCycles) {
      this.maxCycles = maxCycles;
   }

   public int getMaxCycles() {
      return maxCycles;
   }

   public int getCycles() {
      return cycles;
   }

   public void execute(GeppettoProgram program) {
      for (Rule rule : program.getRules()) {
         processRule(rule);
         cycles++;
         if (cycles > maxCycles) {
            print("Maximum number of cycles reached, program terminating.");
            break;
         }
      }
   }
   
   public void processRule(Rule rule) {
      // If rule's condition is true, then execute rule's behavior
      // NB: Condition may contain variables that correspond to entities
      rule.getCondition().getExpression().getValue();
      
   }
   
   public void print(String s) {
      System.out.println(s);
   }
}
