package org.geppetto.domain.expression;

import java.util.ArrayList;
import org.geppetto.GeppettoException;
import org.geppetto.GeppettoProgram;
import org.geppetto.ProgramContext;
import org.geppetto.domain.declaration.FunctionDefinition;
import org.geppetto.domain.declaration.Value;

/**
 * Functions are expressions rather than statements because they return a value.
 * Therefore a function is executed when its value is requested. Functions arguments
 * effectively become local variables.  Return values are placed into the current 
 * program context by the return statement.
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
      throw new GeppettoException("Cannot assign a value to this expression");
   }

   @Override
   public Value getValue() {
      // Get the definition of the function from the AST. 
      FunctionDefinition functionDef = GeppettoProgram.getInstance().getFunctionDefinition(getName());
      if (functionDef == null)
         throw new GeppettoException("Attempting to call undeclared function: " + getName());
      
      // Add a new context to the stack, call the function, then pop the context off the stack.
      // (We still have a reference to the context, so it won't be garbage collected until this function exits.)
      ProgramContext context = new ProgramContext(functionDef, getArguments());
      GeppettoProgram.getInstance().getContexts().add(context);
      functionDef.getCompoundStatement().execute();
      GeppettoProgram.getInstance().getContexts().removeLast();
      
      // If the end statement was not called, check the function's return value.
      if (!GeppettoProgram.getInstance().isEndRequested()) {
         if (context.getReturnValue() == null)
            throw new GeppettoException("Function '" + functionDef.getName() + "' did not set a return value.");
         else if (context.getReturnValue().getType() != functionDef.getType())
            throw new GeppettoException("Type mismatch: Function '" + functionDef.getName() + "' is declared as type "
                  + functionDef.getType() + " but returned value of type " + context.getReturnValue().getType() + ".");
      }

      // Return the function's return value.
      return context.getReturnValue();
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
