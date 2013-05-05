package org.geppetto.domain.expression;

import org.geppetto.domain.Value;


public class AssignmentExpression implements Expression {
   private Expression lvalue;
   private Expression rvalue;

   @SuppressWarnings("unused")
   private AssignmentExpression() {
   }

   public AssignmentExpression(Expression lvalue, Expression rvalue) {
      this.lvalue = lvalue;
      this.rvalue = rvalue;
   }

   public Expression getLvalue() {
      return lvalue;
   }

   public Expression getRvalue() {
      return rvalue;
   }

   @Override
   public boolean isLValue() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void setValue(Value value) {
      // TODO Auto-generated method stub
   }

   @Override
   public Value getValue() {
      // TODO Auto-generated method stub
      return null;
   }
   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("lvalue: ").append(getLvalue());
      sb.append("; rvalue: ").append(getRvalue());
      sb.append("}");
      
      return sb.toString();
   }

}
