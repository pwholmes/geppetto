package org.geppetto.domain.expression;

import org.geppetto.domain.Value;

public class BinaryExpression implements Expression {
   private Expression operand1;
   private Operator operator;
   private Expression operand2;
   
   @SuppressWarnings("unused")
   private BinaryExpression() {}
   
   public BinaryExpression(Expression operand1, Operator operator, Expression operand2) {
      this.operand1 = operand1;
      this.operator = operator;
      this.operand2 = operand2;
   }

   public Expression getOperand1() {
      return operand1;
   }

   public Operator getOperator() {
      return operator;
   }

   public Expression getOperand2() {
      return operand2;
   }

   @Override
   public Value evaluate() {
      // TODO Auto-generated method stub
      return null;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("operand1: ").append(getOperand1());
      sb.append("; operator: ").append(getOperator());
      sb.append("; operand2: ").append(getOperand2());
      sb.append("}");
      
      return sb.toString();
   }
}
