package org.geppetto.domain.expression;

import org.geppetto.GeppettoException;
import org.geppetto.domain.DataType;
import org.geppetto.domain.declaration.Value;

public class LengthExpression implements Expression {
   Expression expression;

   @SuppressWarnings("unused")
   private LengthExpression() {
   }
   
   public LengthExpression(Expression expression) {
      this.expression = expression;
   }
   
   public Expression getExpression() {
      return expression;
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
      if (expression.getValue().getType() != DataType.STRING)
         throw new GeppettoException("Type mismatch: length function expects an argument of type STRING, but it was passed an argument of type " + expression.getValue().getType());
      return new Value(expression.getValue().getStringValue().length());
   }
   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("expression: ").append(getExpression());
      sb.append("}");
      
      return sb.toString();
   }   

}
