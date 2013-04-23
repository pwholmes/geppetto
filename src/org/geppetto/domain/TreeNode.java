package org.geppetto.domain;

import java.util.LinkedList;
import org.geppetto.domain.Phrase;

public class TreeNode {
   LinkedList<TreeNode> children;
   Phrase               phrase;
   // int ival;
   // double dval;
   // String sval;
   // char cval;
   Object               data;

   public TreeNode(Phrase phrase) {
      children = new LinkedList<TreeNode>();
      this.phrase = phrase;
   }

   public TreeNode(Phrase phrase, Object data) {
      this(phrase);
      this.data = data;
   }

   public TreeNode(Phrase phrase, Object data, Object child1) {
      this(phrase);
      this.data = data;
      addChild(child1);
   }

   public TreeNode(Phrase phrase, Object data, Object child1, Object child2) {
      this(phrase);
      this.data = data;
      addChild(child1);
      addChild(child2);
   }

   public TreeNode(Phrase phrase, Object data, Object child1, Object child2,
         Object child3) {
      this(phrase);
      this.data = data;
      addChild(child1);
      addChild(child2);
      addChild(child3);
   }

   public TreeNode(Phrase phrase, Object data, Object child1, Object child2,
         Object child3, Object child4) {
      this(phrase);
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
      System.out.print(phrase.name());
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
