package org.geppetto.interpreter;

import org.geppetto.GeppettoException;
import org.geppetto.GeppettoProgram;
import org.geppetto.domain.declaration.Rule;
import org.geppetto.domain.declaration.Value;
import org.geppetto.domain.expression.VariableType;

public class Interpreter {
   private boolean debug;
   private int maxCycles = 100;
   private int cycle = 0; 
   
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

   public int getCycle() {
      return cycle;
   }

   public void execute(GeppettoProgram program) {
      int cycle = 1;
      while (true) {  
         if (debug)
            print("Starting cycle #" + cycle);
         for (Rule rule : program.getRules()) {
            processRule(rule);
            if (program.isEndRequested())
               break;
         }
         cycle++;
         if (program.isEndRequested()) {
            print("End statement encountered.  Terminating program.");
            break;
         }
         else if (cycle > maxCycles) { 
            print("Maximum number of cycles reached.  Terminating program.");
            break;
         }
      }

   }
   
   public void processRule(Rule rule) {
      if (debug)
         print("Processing rule: " + rule.getName());
      Value conditionValue = rule.getCondition().getValue(); 
      if (conditionValue.getType() != VariableType.BOOLEAN)
         throw new GeppettoException("Rule condition must be a boolean expression, but is of type: " + conditionValue.getType());
      if (conditionValue.getBooleanValue()) {
         if (debug)
            print("Condition of rule " + rule.getName() + " is true, executing its behavior...");
         rule.getBehavior().execute();
      }
   }
   
   public void print(String s) {
      System.out.println(s);
   }
}
