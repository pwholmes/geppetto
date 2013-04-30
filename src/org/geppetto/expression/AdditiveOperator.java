package org.geppetto.expression;

// Todo: segment into unaryops, binary ops, logical ops ...
public enum AdditiveOperator {
   PLUS, MINUS;

   String name;

   AdditiveOperator() {
   }

   AdditiveOperator(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
