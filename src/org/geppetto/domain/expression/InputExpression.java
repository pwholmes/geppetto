package org.geppetto.domain.expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.geppetto.GeppettoException;
import org.geppetto.domain.declaration.Value;

public class InputExpression implements Expression {
   
   public InputExpression() {
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
      String inputString = null;
      try {
         BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
         inputString = bufferRead.readLine();
      } catch (IOException e) {
         throw new GeppettoException("Error retrieving user input from console.", e);
      }
      return new Value(inputString);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName());
      sb.append("}");
      
      return sb.toString();
   }
   
}
