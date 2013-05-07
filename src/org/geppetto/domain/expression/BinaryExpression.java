package org.geppetto.domain.expression;

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
      throw new IllegalArgumentException("Cannot assign a value to an expression that is not an l-value");
   }

   @Override
   public Value getValue() {
      Value value1 = operand1.getValue();
      Value value2 = operand2.getValue();

      switch (operator) {
         case ADD:
            return new Value(value1.getiValue() + value2.getiValue());
            // TODO: ADD
            //throw new IllegalArgumentException("Feature not implemented yet.");
            
         case ASSIGNMENT: // as with C, we'll go with the convention that the value of an assignment expression is the value assigned to the lvalue
            if (!operand1.isLValue())
               throw new IllegalArgumentException("Left operand of an assignment must be a variable, is: " + operand1.getClass().getSimpleName() + ".");
            if (value1.getType() != value2.getType())
               throw new IllegalArgumentException("Type mismatch: attempting to assign value of type " + value2.getType() + " to variable of type " + value1.getType() + ".");
            operand1.setValue(value2.copy());
            return value2.copy();

         case DIVIDE:
            // TODO: DIVIDE
            throw new IllegalArgumentException("Feature not implemented yet.");
         
         case EQUAL_TO:
            if (value1.getType() != value2.getType())
               throw new IllegalArgumentException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.equals(value2));
         
         case GREATER_THAN:
            if (value1.getType() != value2.getType())
               throw new IllegalArgumentException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.compareTo(value2) > 0);
         
         case GREATER_THAN_OR_EQUAL_TO:
            if (value1.getType() != value2.getType())
               throw new IllegalArgumentException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.compareTo(value2) >= 0);
         
         case LESS_THAN:
            if (value1.getType() != value2.getType())
               throw new IllegalArgumentException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.compareTo(value2) < 0);
         
         case LESS_THAN_OR_EQUAL_TO:
            if (value1.getType() != value2.getType())
               throw new IllegalArgumentException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(value1.compareTo(value2) <= 0);
         
         case LOGICAL_AND:
            if (value1.getType() != VariableType.BOOLEAN)
               throw new IllegalArgumentException("Illegal operand type " + value1.getType() + " for operator " + operator);
            if (value2.getType() != VariableType.BOOLEAN)
               throw new IllegalArgumentException("Illegal operand type " + value2.getType() + " for operator " + operator);
            return new Value(value1.getbValue() && value2.getbValue());
         
         case LOGICAL_OR:
            if (value1.getType() != VariableType.BOOLEAN)
               throw new IllegalArgumentException("Illegal operand type " + value1.getType() + " for operator " + operator);
            if (value2.getType() != VariableType.BOOLEAN)
               throw new IllegalArgumentException("Illegal operand type " + value2.getType() + " for operator " + operator);
            return new Value(value1.getbValue() || value2.getbValue());
         
         case MODULUS:
            // TODO: MODULUS
            throw new IllegalArgumentException("Feature not implemented yet.");

         case MULTIPLY:
            // TODO: MULTIPLY
            throw new IllegalArgumentException("Feature not implemented yet.");
         
         case NOT_EQUAL_TO:
            if (value1.getType() != value2.getType())
               throw new IllegalArgumentException("Type mismatch: attempting to compare value of type " + value1.getType() + " to value of type " + value2.getType() + ".");
            return new Value(!value1.equals(value2));
         
         case SUBTRACT:
            // TODO: SUBTRACT
            throw new IllegalArgumentException("Feature not implemented yet.");
         
         default: // should never happen
            throw new IllegalArgumentException("Unknown binary operator: " + operator);
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
