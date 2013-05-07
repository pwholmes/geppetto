package org.geppetto.domain.declaration;

import java.util.ArrayList;
import org.geppetto.domain.DataType;
import org.geppetto.domain.statement.CompoundStatement;

public class FunctionDefinition {
   private String name;
   private DataType type;
   private ArrayList<ArgumentDeclaration> arguments;
   private CompoundStatement compoundStatement;
   
   @SuppressWarnings("unused")
   private FunctionDefinition() {
   }
   
   public FunctionDefinition(String name, DataType type, ArrayList<ArgumentDeclaration> arguments, CompoundStatement compoundStatement) {
      this.name = name;
      this.type = type;
      this.arguments = arguments;
      this.compoundStatement = compoundStatement;
   }

   public String getName() {
      return name;
   }

   public DataType getType() {
      return type;
   }

   public ArrayList<ArgumentDeclaration> getArguments() {
      return arguments;
   }

   public CompoundStatement getCompoundStatement() {
      return compoundStatement;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; type: ").append(getType());
      sb.append("; arguments: ").append(getArguments());
      sb.append("; compoundStatement: ").append(getCompoundStatement());
      sb.append("}");
      
      return sb.toString();
   }   
}
