package org.geppetto.domain.expression;

import java.util.ArrayList;
import org.geppetto.domain.FunctionDefinition;
import org.geppetto.domain.GeppettoProgram;
import org.geppetto.domain.Value;

/**
 * Functions are expressions rather than statements because they return a value.
 * Therefore a function is executed when its value is requested.
 * The other problems associated with functions are arguments, which effectively
 * become scoped variables, and return values.
 * We've avoided the problem of scoping by requiring that all variables be global,
 * but we can't avoid it here.  So those problems have yet to be solved.
 */
public class FunctionExpression implements Expression {
   private String                name;
   private ArrayList<Expression> arguments;

   protected FunctionExpression() {
   }

   public FunctionExpression(String name, ArrayList<Expression> argumentList) {
      this.name = name;
      this.arguments = argumentList;
   }

   public String getName() {
      return name;
   }

   public ArrayList<Expression> getArguments() {
      return arguments;
   }

   @Override
   public boolean isLValue() {
      return false;
   }       

   @Override
   public void setValue(Value value) {
      throw new IllegalArgumentException("Cannot assign a value to this expression");
   }

   @Override
   public Value getValue() {
      FunctionDefinition functionDef = GeppettoProgram.getInstance().getFunctionDefinition(getName());
      if (functionDef == null)
         throw new IllegalArgumentException("Attempting to call undeclared function: " + getName());
      functionDef.getCompoundStatement().execute();
      // TODO: Figure out how to deal with function arguments and return values!
      return null;
   }
   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; arguments: ").append(getArguments());
      sb.append("}");
      
      return sb.toString();
   }

}
