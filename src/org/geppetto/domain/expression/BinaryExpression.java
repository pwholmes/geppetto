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
   public boolean isLValue() {
      return false;
   }

   @Override
   public void setValue(Value value) {
      throw new IllegalArgumentException("Cannot assign a value to an expression that is not an l-value");
   }

   @Override
   public Value getValue() {
      Value value1 = operand1.getValue();
      Value value2 = operand2.getValue();
      Value rv = null;

      switch (operator) {
         case ASSIGNMENT:
            if (!operand1.isLValue())
               throw new IllegalArgumentException("Left operand of assignment must be a variable.");
            if (value1.getType() != value2.getType())
               throw new IllegalArgumentException("Type mismatch: attempring to assign type " + value2.getType() + " to variable of type " + value1.getType() + ".");
            operand1.setValue(value2.copy());
            rv = value2.copy();
            break;
         case DIVIDE:
            break;
         case EQUAL_TO:
            break;
         case GREATER_THAN:
            break;
         case GREATER_THAN_OR_EQUAL_TO:
            break;
         case LESS_THAN:
            break;
         case LESS_THAN_OR_EQUAL_TO:
            break;
         case LOGICAL_AND:
            break;
         case LOGICAL_OR:
            if (value1.getType() != VariableType.BOOLEAN)
               throw new IllegalArgumentException("Illegal operand type " + value1.getType() + " for operator " + operator);
            if (value2.getType() != VariableType.BOOLEAN)
               throw new IllegalArgumentException("Illegal operand type " + value2.getType() + " for operator " + operator);
            rv = new Value(value1.getbValue() || value2.getbValue());
            break;
         case MINUS:
            break;
         case MODULUS:
            break;
         case MULTIPLY:
            break;
         case NOT_EQUAL_TO:
            break;
         case PLUS:
            break;
         case UNARY_MINUS:
            break;
         case UNARY_NEGATION:
            break;
         case UNARY_PLUS:
            break;
         default: // should never happen
            throw new IllegalArgumentException("Unknown operator");
      }
      return rv;
   }
   
   @Override
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
