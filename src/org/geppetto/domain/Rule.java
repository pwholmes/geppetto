package org.geppetto.domain;

import org.geppetto.domain.expression.Expression;
import org.geppetto.domain.statement.Statement;

public class Rule {
   private Expression condition;
   private Statement behavior;
   
   @SuppressWarnings("unused")
   private Rule() {}
   
   public Rule(Expression condition, Statement behavior) {
      this.condition = condition;
      this.behavior = behavior;
   }
   
   public Expression getCondition() {
      return condition;
   }
   
   public Statement getBehavior() {
      return behavior;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("condition: ").append(getCondition());
      sb.append("; behavior: ").append(getBehavior());
      sb.append("}");
      
      return sb.toString();
   }
}
