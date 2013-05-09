package org.geppetto.domain.statement;

import java.util.ArrayList;
import org.geppetto.GeppettoProgram;
import org.geppetto.ProgramContext;
import org.geppetto.domain.declaration.VariableDeclaration;

public class CompoundStatement implements Statement {
   private ArrayList<VariableDeclaration> variables;
   private ArrayList<Statement> statements;

   @SuppressWarnings("unused")
   private CompoundStatement() {
   }
   
   public CompoundStatement(ArrayList<VariableDeclaration> variables, ArrayList<Statement> statements) {
      this.variables = variables;
      this.statements = statements;
   }
   
   public ArrayList<VariableDeclaration> getVariables() {
      return variables;
   }
   
   public ArrayList<Statement> getStatements() {
      return statements;
   }

   @Override
   public void execute() {
      // Add a new program context (because the statement block may have local variables).
      ProgramContext context = new ProgramContext("<CompoundStatement>", variables);
      GeppettoProgram.getInstance().getContexts().add(context);

      // Execute the statements in this statement block.
      for (Statement statement : getStatements()) {
         statement.execute();
         if (GeppettoProgram.getInstance().isEndRequested() || context.isReturnCalled())
            break;
      }
      
      // Pop the new context off the stack. 
      GeppettoProgram.getInstance().getContexts().removeLast();
      
      // If the statement block had a return value, pass it on to the next higher context.
      // This is a hack, caused by the fact that a function and the compound statement it contains each
      // cause the creation of their own ProgramContext.  There should only be one, but I'm not sure how to
      // do that just yet without breaking all kinds of things.
      // TODO Fix the hack where a function has two nested ProgramContexts instead of just one.
      GeppettoProgram.getInstance().getContexts().getLast().setReturnValue(context.getReturnValue());
      GeppettoProgram.getInstance().getContexts().getLast().setReturnCalled(context.isReturnCalled());
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("variables: ").append(getVariables());
      sb.append("; statements: ").append(getStatements());
      sb.append("}");
      
      return sb.toString();
   }   
   
}
