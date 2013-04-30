package org.geppetto.expression;

public class Constant {

   private int   i;
   private float f;

   private Constant() {
   }

   public Constant(int i) {
      this.i = i;
   }

   public Constant(float f) {
      this.f = f;
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
