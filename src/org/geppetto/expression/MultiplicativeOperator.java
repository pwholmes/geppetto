package org.geppetto.expression;

// Todo: segment into unaryops, binary ops, logical ops ...
public enum MultiplicativeOperator {

   MULT, DIV, MOD;

   String name;

   MultiplicativeOperator() {
   }

   MultiplicativeOperator(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
