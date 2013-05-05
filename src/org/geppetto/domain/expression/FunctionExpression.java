package org.geppetto.domain.expression;

import java.util.ArrayList;
import org.geppetto.domain.Value;

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
      // TODO Auto-generated method stub
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
