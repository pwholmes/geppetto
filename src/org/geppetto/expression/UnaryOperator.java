package org.geppetto.expression;

// Todo: segment into unaryops, binary ops, logical ops ...
public enum UnaryOperator {
   UNARY_PLUS, UNARY_MINUS, NOT;

   String name;

   UnaryOperator() {
   }

   UnaryOperator(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
