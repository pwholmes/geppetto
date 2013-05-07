package org.geppetto.domain.expression;

import org.geppetto.domain.GeppettoProgram;
import org.geppetto.domain.Value;

/**
 * The Variable class serves for both variable declarations and variable accesses.
 * For other data types (namely Properties) I separated those functions into two separate
 * classes, but in this case I decided to do both jobs in one class.
 * Variable *declarations* only use the constructor (not setValue/getValue), and only declarations 
 * actually have a Value.  Declarations are stored in the symbol table.  
 * Variable *accesses* use setValue/getValue, which accesses the declared Variable in the symbol table.
 * If this is too problematic or confusing I'll just create a VariableDeclaration class and
 * separate the functionality.
 */
public class Variable implements Expression {
   private VariableType type;
   private String name;
   private Value value;

   public Variable(String name, VariableType type, Value value) {
      this.name = name;
      this.type = type;
      this.value = value;
   }

   public Variable(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public VariableType getType() {
      return type;
   }

   public void setType(VariableType type) {
      this.type = type;
   }

   @Override
   public boolean isLValue() {
      return true;
   }   

   @Override
   public void setValue(Value value) {
      Variable variable = GeppettoProgram.getInstance().getVariable(getName());
      if (variable == null)
         throw new IllegalArgumentException("Undeclared variable: " + getName());
      variable.value = value;  // DON'T call setValue() here or we'll go into an infinite loop!
   }
   
   @Override
   public Value getValue() {
      Variable variable = GeppettoProgram.getInstance().getVariable(getName());
      if (variable == null)
         throw new IllegalArgumentException("Undeclared variable: " + getName());
      return variable.value; // DON'T call getValue() here or we'll go into an infinite loop!
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; type: ").append(getType());
      sb.append("; value: ").append(getValue());
      sb.append("}");
      
      return sb.toString();
   }

}
