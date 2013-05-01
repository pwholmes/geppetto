package org.geppetto.domain.expression;

import org.geppetto.domain.Value;

public class UnaryExpression implements Expression {
   private Operator operator;
   private Expression operand;
   
   @SuppressWarnings("unused")
   private UnaryExpression() {}
   
   public UnaryExpression(Operator operator, Expression operand) {
      this.operator = operator;
      this.operand = operand;
   }

   public Operator getOperator() {
      return operator;
   }

   public Expression getOperand() {
      return operand;
   }

   @Override
   public Value evaluate() {
      // TODO Auto-generated method stub
      return null;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("operator: ").append(getOperator());
      sb.append("; operand: ").append(getOperand());
      sb.append("}");
      
      return sb.toString();
   }}
