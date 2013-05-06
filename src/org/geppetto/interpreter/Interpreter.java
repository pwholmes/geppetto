package org.geppetto.interpreter;

import org.geppetto.domain.GeppettoProgram;
import org.geppetto.domain.Rule;
import org.geppetto.domain.Value;
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
      boolean proceed = true;
      while (proceed && cycle < maxCycles) {  
         if (debug)
            print("Starting cycle #" + cycle);
         for (Rule rule : program.getRules()) {
            proceed = processRule(rule);
            if (!proceed)
               break;
         }
         if (!proceed)
            print("End statement encountered.  Terminating program.");
         else {
            cycle++;
            if (cycle > maxCycles) 
               print("Maximum number of cycles reached.  Terminating program.");
         }
      }

   }
   
   public boolean processRule(Rule rule) {
      if (debug)
         print("Processing rule...");
      Value conditionValue = rule.getCondition().getExpression().getValue(); 
      if (conditionValue.getType() != VariableType.BOOLEAN)
         throw new IllegalArgumentException("Rule condition must be a boolean expression, but is of type: " + conditionValue.getType());
      if (conditionValue.getbValue()) {
         if (debug)
            print("Rule condition is true, executing its behavior...");
         return rule.getBehavior().getStatement().execute();
      }
      return true;
   }
   
   public void print(String s) {
      System.out.println(s);
   }
}
