package org.geppetto.parser;

public class Tree {
   TreeNode root;

   protected Tree() {
   }

   public Tree(TreeNode root) {
      this.root = root;
   }

   public void print() {
      root.print("|--");
   }

   public void setRoot(Object tn) {
      root = (TreeNode) tn;
   }
}
