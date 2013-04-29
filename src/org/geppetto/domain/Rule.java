package org.geppetto.domain;

public class Rule {
   private Condition condition;
   private Behavior behavior;
   
   @SuppressWarnings("unused")
   private Rule() {}
   
   public Rule(Condition condition, Behavior behavior) {
      this.condition = condition;
      this.behavior = behavior;
   }
   
   public Condition getCondition() {
      return condition;
   }
   
   public Behavior getBehavior() {
      return behavior;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("condition: ").append(getCondition());
      sb.append("; behavior: ").append(getBehavior());
      
      return sb.toString();
   }
}
