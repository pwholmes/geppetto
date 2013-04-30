package org.geppetto.expression;

public class Identifier {

   private int id;

   private Identifier() {

   }

   public Identifier(int id) {
      this.id = id;
   }

   // public boolean evaluate() {
   //
   // }
   //
   // public boolean checkSyntax() {
   //
   // }
   //
   // public String toString() {
   //
   // }

   public void print() {
      System.out.println(toString());
   }
}
