package org.geppetto.domain;

import org.geppetto.parser.Tree;

public class Condition {
   private Tree abstractSyntaxTree;
   
   @SuppressWarnings("unused")
   private Condition() {}
   
   public Condition(Tree abstractSyntaxTree) {
      this.abstractSyntaxTree = abstractSyntaxTree;
   }
   
   public Tree getAbstractSyntaxTree() {
      return abstractSyntaxTree;
   }
}
