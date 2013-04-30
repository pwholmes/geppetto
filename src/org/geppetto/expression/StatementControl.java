package org.geppetto.expression;

public enum StatementControl {
   IF, ELSE, WHILE, FOREACH;

   String name;

   StatementControl() {
   }

   StatementControl(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
