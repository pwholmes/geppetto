package org.geppetto.domain.expression;

import java.util.ArrayList;
import org.geppetto.GeppettoException;
import org.geppetto.GeppettoProgram;
import org.geppetto.ProgramContext;
import org.geppetto.domain.declaration.FunctionDefinition;
import org.geppetto.domain.declaration.Value;

/**
 * Functions are expressions rather than statements because they return a value.
 * Therefore a function is executed when its value is requested. The other
 * problems associated with functions are arguments, which effectively become
 * scoped variables, and return values. We've avoided the problem of scoping by
 * requiring that all variables be global, but we can't avoid it here. So those
 * problems have yet to be solved.
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
      FunctionDefinition functionDef = GeppettoProgram.getInstance().getFunctionDefinition(getName());
      if (functionDef == null)
         throw new GeppettoException("Attempting to call undeclared function: " + getName());
      ProgramContext context = new ProgramContext(functionDef);
      GeppettoProgram.getInstance().getContexts().add(context);
      functionDef.getCompoundStatement().execute();
      GeppettoProgram.getInstance().getContexts().removeLast();
      if (!GeppettoProgram.getInstance().isEndRequested()) {
         if (context.getReturnValue() == null)
            throw new GeppettoException("Function '" + functionDef.getName() + "' did not set a return value.");
         else if (context.getReturnValue().getType() != functionDef.getType())
            throw new GeppettoException("Type mismatch: Function '" + functionDef.getName() + "' is declared as type "
                  + functionDef.getType() + " but returned value of type " + context.getReturnValue().getType() + ".");
      }

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
