package org.geppetto.domain;

public class Behavior {
   public Behavior() {}
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append(getClass().getSimpleName()).append(": ");
      
      return sb.toString();
   }
}
