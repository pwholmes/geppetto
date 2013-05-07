package org.geppetto.domain.expression;

import org.geppetto.GeppettoException;
import org.geppetto.GeppettoProgram;
import org.geppetto.domain.declaration.Value;
import org.geppetto.domain.declaration.VariableDeclaration;

public class VariableExpression implements Expression {
   private String name;

   public VariableExpression(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public boolean isLValue() {
      return true;
   }   

   @Override
   public void setValue(Value value) {
      VariableDeclaration variable = GeppettoProgram.getInstance().getVariableDeclaration(getName());
      if (variable == null)
         throw new GeppettoException("Undeclared variable: " + getName());
      variable.setValue(value);
   }
   
   @Override
   public Value getValue() {
      VariableDeclaration variable = GeppettoProgram.getInstance().getVariableDeclaration(getName());
      if (variable == null)
         throw new GeppettoException("Undeclared variable: " + getName());
      return variable.getValue();
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; value: ").append(getValue());
      sb.append("}");
      
      return sb.toString();
   }

}
