package org.geppetto;

import java.util.ArrayList;
import java.util.Iterator;
import org.geppetto.domain.declaration.ArgumentDeclaration;
import org.geppetto.domain.declaration.FunctionDefinition;
import org.geppetto.domain.declaration.Value;
import org.geppetto.domain.declaration.VariableDeclaration;
import org.geppetto.domain.expression.Expression;

public class ProgramContext {
   private String name;
   private ArrayList<VariableDeclaration> variables;
   private Value returnValue;
   
   public ProgramContext(String name, ArrayList<VariableDeclaration> variables) {
      this.name = name;
      this.variables = variables;
   }

   /**
    * This constructor creates a new program context given the definition of a function.  Optimally
    * we'd allow new local variables to be declared in the function call as well, but for now the only
    * local variables allowed in the new context are those from the function's own arguments.  We
    * create new local variables with the names of the arguments in the function definition, and values
    * taken from the arguments in the function expression.  Note that the arguments in the function 
    * *definition* need not have the same names as those in the function *expression*, so they are 
    * assigned values in the order of their declaration on a one-to-one basis.  Also note that each 
    * value is a *COPY* of the argument's Value, not the Value itself, so this is call-by-value.  
    */
   public ProgramContext(FunctionDefinition functionDef, ArrayList<Expression> arguments) {
      this.name = functionDef.getName();
      this.variables = new ArrayList<VariableDeclaration>();
      Iterator<Expression> argumentIterator = arguments.iterator();
      for (ArgumentDeclaration argDecl : functionDef.getArguments()) {
         // Create a new local variable with the type and name of the argument in the function definition
         VariableDeclaration variable = new VariableDeclaration(argDecl.getName(), argDecl.getType());
         
         // Get the value to assign to this new variable from the corresponding function argument.
         // We'll allow implicit type conversion in the argument, provided the conversion is legal.
         // If it's not legal an exception will be thrown by the getter.
         Expression argument = argumentIterator.next();
         switch (variable.getType()) {
            case BOOLEAN:
               variable.setValue(new Value(argument.getValue().getBooleanValue()));
               break;
            case FLOAT:
               variable.setValue(new Value(argument.getValue().getFloatValue()));
               break;
            case INT:
               variable.setValue(new Value(argument.getValue().getIntValue()));
               break;
            case STRING:
               variable.setValue(new Value(argument.getValue().getStringValue()));
               break;
         }
         
         // Add the new variable to the context.
         variables.add(variable);
      }
   }
   
   public String getName() {
      return name;
   }
   
   public ArrayList<VariableDeclaration> getVariableDeclarations() {
      return variables;
   }
   
   public Value getReturnValue() {
      return returnValue;
   }
   
   public void setReturnValue(Value returnValue) {
      this.returnValue = returnValue;
   }
   
}
