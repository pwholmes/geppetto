package org.geppetto.domain.expression;

public class BinaryExpression implements Expression {
   private Expression operand1;
   private Operator operator;
   private Expression operand2;
   
   private BinaryExpression() {}
   
   public BinaryExpression(Expression operand1, Operator operator, Expression operand2) {
      this.operand1 = operand1;
      this.operator = operator;
      this.operand2 = operand2;
   }
}
