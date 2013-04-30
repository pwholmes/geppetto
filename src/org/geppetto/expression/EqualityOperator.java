package org.geppetto.expression;

// Todo: segment into unaryops, binary ops, logical ops ...
public enum EqualityOperator {
   EQUALTO, NOT_EQUALTO;

   String name;

   EqualityOperator() {
   }

   EqualityOperator(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
