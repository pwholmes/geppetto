package org.geppetto.domain.expression;

public class UnaryExpression implements Expression {
   private Operator operator;
   private Expression operand;
   
   private UnaryExpression() {}
   
   public UnaryExpression(Operator operator, Expression operand) {
      this.operator = operator;
      this.operand = operand;
   }
}
