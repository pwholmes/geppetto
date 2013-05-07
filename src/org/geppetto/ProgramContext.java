package org.geppetto;

import java.util.ArrayList;
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
