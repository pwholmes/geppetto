package org.geppetto.domain.expression;

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
      throw new IllegalArgumentException("Cannot assign a value to this expression");
   }
   
   @Override
   public Value getValue() {
      Value value = operand.getValue();

      switch (operator) {
         case UNARY_MINUS:
            if (value.getType() == VariableType.INT)
               return new Value(-value.getiValue());
            else if (value.getType() == VariableType.FLOAT)
               return new Value(-value.getfValue());
            else
               throw new IllegalArgumentException("Illegal operand type " + value.getType() + " for operator " + operator + ".");
   
         case UNARY_NEGATION:
            if (value.getType() != VariableType.BOOLEAN)
               throw new IllegalArgumentException("Illegal operand type " + value.getType() + " for operator " + operator + ".");
            return new Value(!value.getbValue());
         
         case UNARY_PLUS: // why do we even have this?
            if (value.getType() == VariableType.INT)
               return new Value(-value.getiValue());
            else if (value.getType() == VariableType.FLOAT)
               return new Value(value.getfValue());
            else
               throw new IllegalArgumentException("Illegal operand type " + value.getType() + " for operator " + operator + ".");
            
         default:
            throw new IllegalArgumentException("Unknown unary operator: " + operator);
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
