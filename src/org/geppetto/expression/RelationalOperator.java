package org.geppetto.expression;

// Todo: segment into unaryops, binary ops, logical ops ...
public enum RelationalOperator {

   GREATER_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL;

   String name;

   RelationalOperator() {
   }

   RelationalOperator(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
