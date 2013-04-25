package org.geppetto.parser;

public enum TreeNodeType {
   PROGRAM, 
   ATTRIBUTE_LIST, ENTITY_LIST, RULE_LIST, STATEMENT_LIST,
   ATTRIBUTE, ENTITY, RULE, STATEMENT,
   EXPRESSION,
   NUM, PLUS, MINUS, MULT, DIV, NEG, POW, 
   LPAREN, RPAREN;

   String name;

   TreeNodeType() {
   }

   TreeNodeType(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
