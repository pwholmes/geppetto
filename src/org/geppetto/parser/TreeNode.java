package org.geppetto.parser;

import java.util.LinkedList;

public class TreeNode {
   private LinkedList<TreeNode> children;
   private TreeNodeType         type;
   // int ival;
   // double dval;
   // String sval;
   // char cval;
   private Object               data;

   public TreeNode(TreeNodeType type) {
      children = new LinkedList<TreeNode>();
      this.type = type;
   }

   public TreeNode(TreeNodeType type, Object data) {
      this(type);
      this.data = data;
   }

   public TreeNode(TreeNodeType type, Object data, Object child1) {
      this(type);
      this.data = data;
      addChild(child1);
   }

   public TreeNode(TreeNodeType type, Object data, Object child1, Object child2) {
      this(type);
      this.data = data;
      addChild(child1);
      addChild(child2);
   }

   public TreeNode(TreeNodeType type, Object data, Object child1, Object child2, Object child3) {
      this(type);
      this.data = data;
      addChild(child1);
      addChild(child2);
      addChild(child3);
   }

   public TreeNode(TreeNodeType type, Object data, Object child1, Object child2, Object child3, Object child4) {
      this(type);
      this.data = data;
      addChild(child1);
      addChild(child2);
      addChild(child3);
      addChild(child4);
   }

   // public TreeNode(Phrase phrase, int ival)
   // {
   // this.phrase = phrase;
   // this.ival = ival;
   // }
   //
   // public TreeNode(Phrase phrase, double dval)
   // {
   // this.phrase = phrase;
   // this.dval = dval;
   // }
   //
   // public TreeNode(Phrase phrase, String sval)
   // {
   // this.phrase = phrase;
   // this.sval = sval;
   // }
   //
   // public TreeNode(Phrase phrase, char cval)
   // {
   // this.phrase = phrase;
   // this.cval = cval;
   // }

   public void addChild(TreeNode tn) {
      children.add(tn);
   }

   public void addChild(Object o) {
      addChild((TreeNode) o);
   }

   public void print(String s) {
      System.out.print(type.name());
      if (data != null) {
         System.out.print(", " + data);
      }
      System.out.print("\n");

      for (TreeNode tn : children) {
         System.out.print(s);
         tn.print("|  " + s);
      }
   }
}
