package org.geppetto.expression;

public class StructureExpression extends Expression {

   private String firstIdentifier;
   private String secondIdentifier;
   private String thirdIdentifier;

   private StructureExpression() {
   }

   public StructureExpression(String firstIdentifier, String secondIdentifier) {
      this.firstIdentifier = firstIdentifier;
      this.secondIdentifier = secondIdentifier;
      thirdIdentifier = null;
   }

   public StructureExpression(String firstIdentifier,
         String secondIdentifier, String thirdIdentifier) {
      this.firstIdentifier = firstIdentifier;
      this.secondIdentifier = secondIdentifier;
      this.thirdIdentifier = thirdIdentifier;
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
