package org.geppetto.domain.expression;

import org.geppetto.GeppettoException;
import org.geppetto.domain.DataType;
import org.geppetto.domain.Operator;
import org.geppetto.domain.declaration.Value;


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
   public boolean isLValue() {
      return false;
   }

   @Override
   public void setValue(Value value) {
      throw new GeppettoException("Cannot assign a value to this expression");
   }
   
   @Override
   public Value getValue() {
      Value value = operand.getValue();

      switch (operator) {
         case UNARY_MINUS:
            if (value.getType() == DataType.INT)
               return new Value(-value.getIntValue());
            else if (value.getType() == DataType.FLOAT)
               return new Value(-value.getFloatValue());
            else
               throw new GeppettoException("Illegal operand type " + value.getType() + " for operator " + operator + ".");
   
         case UNARY_NEGATION:
            if (value.getType() != DataType.BOOLEAN)
               throw new GeppettoException("Illegal operand type " + value.getType() + " for operator " + operator + ".");
            return new Value(!value.getBooleanValue());
         
         case UNARY_PLUS: // why do we even have this?
            if (value.getType() == DataType.INT)
               return new Value(-value.getIntValue());
            else if (value.getType() == DataType.FLOAT)
               return new Value(value.getFloatValue());
            else
               throw new GeppettoException("Illegal operand type " + value.getType() + " for operator " + operator + ".");
            
         default:
            throw new GeppettoException("Unknown unary operator: " + operator);
      }

      // Can't get here due to default case in switch statement; adding a return results in an unreachable code error  
   }
   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("operator: ").append(getOperator());
      sb.append("; operand: ").append(getOperand());
      sb.append("}");
      
      return sb.toString();
   }

}
