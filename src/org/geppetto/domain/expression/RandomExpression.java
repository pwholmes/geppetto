package org.geppetto.domain.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.geppetto.GeppettoException;
import org.geppetto.domain.declaration.Value;

/**
 * Note that you can't create three contructors each of which takes a different Set<>
 * as a parameter because that causes a Java type erasure error. 
 */
public class RandomExpression implements Expression {
   private static Random randomGenerator = new Random();
   private ArrayList<Float> floatSet;
   private ArrayList<Integer> integerSet;
   private ArrayList<String> stringSet;

   public RandomExpression() {
   }
   
   public List<Float> getFloatSet() {
      return floatSet;
   }

   public void setFloatSet(Set<Float> floatSet) {
      this.floatSet = new ArrayList<Float>(floatSet);
   }

   public List<Integer> getIntegerSet() {
      return integerSet;
   }

   public void setIntegerSet(Set<Integer> integerSet) {
      this.integerSet = new ArrayList<Integer>(integerSet);
   }

   public List<String> getStringSet() {
      return stringSet;
   }

   public void setStringSet(Set<String> stringSet) {
      this.stringSet = new ArrayList<String>(stringSet);
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
      if (floatSet != null) {
         int index = randomGenerator.nextInt(floatSet.size());
         return new Value(floatSet.get(index));
      } else if (integerSet != null) {
         int index = randomGenerator.nextInt(integerSet.size());
         return new Value(integerSet.get(index));
      } else if (stringSet != null) {
         int index = randomGenerator.nextInt(stringSet.size());
         return new Value(stringSet.get(index));
      } else
         throw new GeppettoException("No constraints given to random function.");
      
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("floatSet: ").append(getFloatSet());
      sb.append("integerSet: ").append(getIntegerSet());
      sb.append("stringSet: ").append(getStringSet());
      sb.append("}");
      
      return sb.toString();
   }

}
