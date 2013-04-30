package org.geppetto.domain.expression;

import java.util.ArrayList;

public class FunctionExpression {
   private String     name;
   private ArrayList<Expression> argumentList;

   protected FunctionExpression() {
   }

   public FunctionExpression(String name, ArrayList<Expression> argumentList) {
      this.name = name;
      this.argumentList = argumentList;
   }
}
