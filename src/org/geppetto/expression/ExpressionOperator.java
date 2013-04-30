package org.geppetto.expression;

// Todo: segment into unaryops, binary ops, logical ops ...
public enum ExpressionOperator {
   UNARY_PLUS, UNARY_MINUS, EXCLAMATION, MULT, DIV, MOD, PLUS, MINUS, GREATERTHAN, GREATERTHAN_OR_EQUAL, LESSTHAN, LESSTHAN_OR_EQUAL, EQUALTO, NOT_EQUALTO, LOGICAL_AND, LOGICAL_OR, ASSIGNMENT;

   String name;

   ExpressionOperator() {
   }

   ExpressionOperator(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
