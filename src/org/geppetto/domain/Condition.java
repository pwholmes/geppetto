package org.geppetto.domain;

public class Condition {
   public Condition() {}

   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append(getClass().getSimpleName()).append(": ");
      
      return sb.toString();
   }
}
