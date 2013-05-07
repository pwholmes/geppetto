package org.geppetto.domain.expression;

import org.geppetto.GeppettoException;
import org.geppetto.domain.declaration.Value;

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
      throw new GeppettoException("Cannot assign a value to an expression that is not an l-value");
   }

   @Override
   public Value getValue() {
      Value value1 = operand1.getValue();
      Value value2 = operand2.getValue();

      switch (operator) {
         case ADD:
            if (value1.getType() == VariableType.STRING || value2.getType() == VariableType.STRING)
               return new Value(value1.getStringValue() + value2.getStringValue());
            else if (value1.getType() == VariableType.BOOLEAN || value2.getType() == VariableType.BOOLEAN)
               throw new GeppettoException("Type mismatch: cannot perform operation " + operator + " with operands of type " + value1.getType() + " and type " + value2.getType() + ".");
            else if (value1.getType() == VariableType.FLOAT || value2.getType() == VariableType.FLOAT)
               return new Value(value1.getFloatValue() + value2.getFloatValue());
            else // only other possibility is two ints
               return new Value(value1.getIntValue() + value2.getIntValue());
            
         case ASSIGNMENT: // as with C, we'll go with the convention that the value of an assignment expression is the value assigned to the lvalue
            if (!operand1.isLValue())
               throw new GeppettoException("Left operand of an assignment must be a variable, is: " + operand1.getClass().getSimpleName() + ".");
            if (value1.getType() != value2.getType())
               throw new GeppettoException("Type mismatch: attempting to assign value of type " + value2.getType() + " to variable of type " + value1.getType() + ".");
            operand1.setValue(value2.copy());
            return value2.copy();

         case DIVIDE:
            if (value1.getType() == VariableType.INT || value2.getType() == VariableType.INT)
               return new Value((int)(value1.getIntValue() / value2.getIntValue()));
            else if ((value1.getType() == VariableType.FLOAT || value1.getType() == VariableType.INT) &&
                     (value2.getType() == VariableType.FLOAT || value2.getType() == VariableType.INT))
               return new Value((float)(value1.getFloatValue() / value2.getFloatValue()));
            else
               throw new GeppettoException("Type mismatch: cannot perform operation " + operator + " with operands of type " + value1.getType() + " and type " + value2.getType() + ".");
         
         case EQUAL_TO:
            if (value1.getType() != value2.getType())
               throw new GeppettoException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.equals(value2));
         
         case GREATER_THAN:
            if (value1.getType() != value2.getType())
               throw new GeppettoException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.compareTo(value2) > 0);
         
         case GREATER_THAN_OR_EQUAL_TO:
            if (value1.getType() != value2.getType())
               throw new GeppettoException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.compareTo(value2) >= 0);
         
         case LESS_THAN:
            if (value1.getType() != value2.getType())
               throw new GeppettoException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.compareTo(value2) < 0);
         
         case LESS_THAN_OR_EQUAL_TO:
            if (value1.getType() != value2.getType())
               throw new GeppettoException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.compareTo(value2) <= 0);
         
         case LOGICAL_AND:
            if (value1.getType() != VariableType.BOOLEAN)
               throw new GeppettoException("Illegal operand type " + value1.getType() + " for operator " + operator);
            if (value2.getType() != VariableType.BOOLEAN)
               throw new GeppettoException("Illegal operand type " + value2.getType() + " for operator " + operator);
            return new Value(value1.getBooleanValue() && value2.getBooleanValue());
         
         case LOGICAL_OR:
            if (value1.getType() != VariableType.BOOLEAN)
               throw new GeppettoException("Illegal operand type " + value1.getType() + " for operator " + operator);
            if (value2.getType() != VariableType.BOOLEAN)
               throw new GeppettoException("Illegal operand type " + value2.getType() + " for operator " + operator);
            return new Value(value1.getBooleanValue() || value2.getBooleanValue());
         
         case MODULUS:
            if (value1.getType() == VariableType.INT || value2.getType() == VariableType.INT)
               return new Value((int)(value1.getIntValue() % value2.getIntValue()));
            else if ((value1.getType() == VariableType.FLOAT || value1.getType() == VariableType.INT) &&
                     (value2.getType() == VariableType.FLOAT || value2.getType() == VariableType.INT))
               return new Value((float)(value1.getFloatValue() % value2.getFloatValue()));
            else
               throw new GeppettoException("Type mismatch: cannot perform operation " + operator + " with operands of type " + value1.getType() + " and type " + value2.getType() + ".");

         case MULTIPLY:
            if (value1.getType() == VariableType.INT || value2.getType() == VariableType.INT)
               return new Value((int)(value1.getIntValue() * value2.getIntValue()));
            else if ((value1.getType() == VariableType.FLOAT || value1.getType() == VariableType.INT) &&
                     (value2.getType() == VariableType.FLOAT || value2.getType() == VariableType.INT))
               return new Value((float)(value1.getFloatValue() * value2.getFloatValue()));
            else
               throw new GeppettoException("Type mismatch: cannot perform operation " + operator + " with operands of type " + value1.getType() + " and type " + value2.getType() + ".");
         
         case NOT_EQUAL_TO:
            if (value1.getType() != value2.getType())
               throw new GeppettoException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(!value1.equals(value2));
         
         case SUBTRACT:
            if (value1.getType() == VariableType.INT || value2.getType() == VariableType.INT)
               return new Value((int)(value1.getIntValue() - value2.getIntValue()));
            else if ((value1.getType() == VariableType.FLOAT || value1.getType() == VariableType.INT) &&
                     (value2.getType() == VariableType.FLOAT || value2.getType() == VariableType.INT))
               return new Value((float)(value1.getFloatValue() - value2.getFloatValue()));
            else
               throw new GeppettoException("Type mismatch: cannot perform operation " + operator + " with operands of type " + value1.getType() + " and type " + value2.getType() + ".");
         
         default: // should never happen
            throw new GeppettoException("Unknown binary operator: " + operator);
      }
      
      // Can't get here due to default case in switch statement; adding a return results in an unreachable code error  
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
