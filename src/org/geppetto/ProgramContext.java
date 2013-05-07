package org.geppetto;

import java.util.ArrayList;
import org.geppetto.domain.declaration.ArgumentDeclaration;
import org.geppetto.domain.declaration.FunctionDefinition;
import org.geppetto.domain.declaration.Value;
import org.geppetto.domain.declaration.VariableDeclaration;

public class ProgramContext {
   private String name;
   private ArrayList<VariableDeclaration> variables;
   private Value returnValue;
   
   public ProgramContext(String name, ArrayList<VariableDeclaration> variables) {
      this.name = name;
      this.variables = variables;
   }

   public ProgramContext(FunctionDefinition functionDef) {
      this.name = functionDef.getName();
      this.variables = new ArrayList<VariableDeclaration>();
      for (ArgumentDeclaration arg : functionDef.getArguments()) {
         variables.add(new VariableDeclaration(arg.getName(), arg.getType()));
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
