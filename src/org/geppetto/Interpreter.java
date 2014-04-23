package org.geppetto;

import org.geppetto.domain.DataType;
import org.geppetto.domain.declaration.Rule;
import org.geppetto.domain.declaration.Value;
import org.geppetto.domain.declaration.VariableDeclaration;

public class Interpreter {
   private static String CYCLE_VAR_NAME = "cycle";
   private static String MAXCYCLES_VAR_NAME = "maxCycles";
   private static int DEFAULT_CYCLE = 1;
   private static int DEFAULT_MAXCYCLES = 100;
   private boolean debug;
   
   public Interpreter(boolean debug) {
      this.debug = debug;
   }
   
   public void setDebug(boolean debug) {
      this.debug = debug;
   }

   public boolean isDebug() {
      return debug;
   }

   public void execute(GeppettoProgram program) {
      configureStandardVariables(program);

      // Note that the whole point of having cycle and maxCycles as Geppetto variables is that the
      // user can set them, so we have to check their values dynamically.
      while (true) {
         if (debug)
            print("Starting cycle #" + getCycle(program));
         for (Rule rule : program.getRules()) {
            processRule(rule);
            if (program.isEndRequested())
               break;
         }
         incrementCycle(program);
         if (program.isEndRequested()) {
            print("End statement encountered.  Terminating program.");
            break;
         }
         else if (getCycle(program) > getMaxCycles(program)) { 
            print("Maximum number of cycles reached.  Terminating program.");
            break;
         }
      }

   }
   
   /**
    * If cycle and maxCycles weren't declared by the user as global variables, do that now and
    * assign them defaut values.
    */
   private void configureStandardVariables(GeppettoProgram program) {
      VariableDeclaration cycleVar = program.getGlobalVariableDeclaration(CYCLE_VAR_NAME);
      if (cycleVar == null) {
         cycleVar = new VariableDeclaration(CYCLE_VAR_NAME, DataType.INT);
         cycleVar.setValue(new Value(DEFAULT_CYCLE));
         program.addGlobalVariableDeclaration(cycleVar);
      }
      
      VariableDeclaration maxCyclesVar = program.getGlobalVariableDeclaration(MAXCYCLES_VAR_NAME);
      if (maxCyclesVar == null) {
         maxCyclesVar = new VariableDeclaration(MAXCYCLES_VAR_NAME, DataType.INT);
         maxCyclesVar.setValue(new Value(DEFAULT_MAXCYCLES));
         program.addGlobalVariableDeclaration(maxCyclesVar);
         }
      }
   
   private int getCycle(GeppettoProgram program) {
      return program.getGlobalVariableDeclaration(CYCLE_VAR_NAME).getValue().getIntValue();
   }

   private void incrementCycle(GeppettoProgram program) {
      VariableDeclaration cycleVar = program.getGlobalVariableDeclaration(CYCLE_VAR_NAME);
      // Note that you can't change the value of a Value once it's been created (it's a value object, after all),
      // so to increment its value we have to create a new Value.  Got it? 
      cycleVar.setValue(new Value(cycleVar.getValue().getIntValue() + 1));
   }
   
   private int getMaxCycles(GeppettoProgram program) {
      return program.getGlobalVariableDeclaration(MAXCYCLES_VAR_NAME).getValue().getIntValue();
   }

   private void processRule(Rule rule) {
      if (debug)
         print("Processing rule: " + rule.getName());
      Value conditionValue = rule.getCondition().getValue(); 
      if (conditionValue.getType() != DataType.BOOLEAN)
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
