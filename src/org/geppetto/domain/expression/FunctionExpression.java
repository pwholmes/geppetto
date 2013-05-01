package org.geppetto.domain.expression;

import java.util.ArrayList;
import org.geppetto.domain.Value;

public class FunctionExpression implements Expression {
   private String                name;
   private ArrayList<Expression> argumentList;

   protected FunctionExpression() {
   }

   public FunctionExpression(String name, ArrayList<Expression> argumentList) {
      this.name = name;
      this.argumentList = argumentList;
   }

   public String getName() {
      return name;
   }

   public ArrayList<Expression> getArgumentList() {
      return argumentList;
   }

   @Override
   public Value evaluate() {
      // TODO Auto-generated method stub
      return null;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; argumentList: (");
      boolean first = true;
      for (Expression expression : getArgumentList()) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(expression);
      }
      sb.append(")}");
      
      return sb.toString();
   }       
}
