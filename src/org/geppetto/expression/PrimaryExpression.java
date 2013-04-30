package org.geppetto.expression;

public class PrimaryExpression extends FunctionExpression {

   private Integer           integ;
   private Double             dbl;
   private BooleanExpression booleanExpression;
   private String            identifier;
   private Expression        expression;

   protected PrimaryExpression() {
   }

   public PrimaryExpression(int integ) {
      this.integ = integ;
      dbl = null;
      this.booleanExpression = null;
      identifier = null;
      expression = null;
   }

   public PrimaryExpression(Double dbl) {
      integ = null;
      this.dbl = dbl;
      identifier = null;
      expression = null;
      this.booleanExpression = null;

   }

   public PrimaryExpression(String identifier) {
      integ = null;
      dbl = null;
      this.identifier = identifier;
      expression = null;
      this.booleanExpression = null;

   }

   public PrimaryExpression(Expression expression) {
      integ = null;
      dbl = null;
      identifier = null;
      this.expression = expression;
      this.booleanExpression = null;

   }

   public PrimaryExpression(BooleanExpression booleanExpression) {
      integ = null;
      dbl = null;
      identifier = null;
      this.expression = null;
      this.booleanExpression = booleanExpression;

   }

   public boolean evaluate() {
      return true;
   }

   public boolean checkSyntax() {
      return true;
   }

   public String toString() {
      return this.toString();
   }

   public void print() {
      System.out.println(toString());
   }
}
