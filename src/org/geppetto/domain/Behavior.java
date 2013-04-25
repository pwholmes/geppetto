package org.geppetto.domain;

import org.geppetto.parser.Tree;

public class Behavior {

   private Tree abstractSyntaxTree;
   
   @SuppressWarnings("unused")
   private Behavior() {}
   
   public Behavior(Tree abstractSyntaxTree) {
      this.abstractSyntaxTree = abstractSyntaxTree;
   }
   
   public Tree getAbstractSyntaxTree() {
      return abstractSyntaxTree;
   }
}
