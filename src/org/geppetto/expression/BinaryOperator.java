package org.geppetto.expression;

// Todo: segment into unaryops, binary ops, logical ops ...
public enum BinaryOperator {
   MULT, DIV, MOD, PLUS, MINUS;

   String name;

   BinaryOperator() {
   }

   BinaryOperator(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
